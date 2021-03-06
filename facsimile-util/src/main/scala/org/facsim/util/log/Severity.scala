//======================================================================================================================
// Facsimile: A Discrete-Event Simulation Library
// Copyright © 2004-2020, Michael J Allen.
//
// This file is part of Facsimile.
//
// Facsimile is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
// version.
//
// Facsimile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
// warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
// details.
//
// You should have received a copy of the GNU Lesser General Public License along with Facsimile. If not, see:
//
//   http://www.gnu.org/licenses/lgpl.
//
// The developers welcome all comments, suggestions and offers of assistance. For further information, please visit the
// project home page at:
//
//   http://facsim.org/
//
// Thank you for your interest in the Facsimile project!
//
// IMPORTANT NOTE: All patches (modifications to existing files and/or the addition of new files) submitted for
// inclusion as part of the official Facsimile code base, must comply with the published Facsimile Coding Standards. If
// your code fails to comply with the standard, then your patches will be rejected. For further information, please
// visit the coding standards at:
//
//   http://facsim.org/Documentation/CodingStandards/
//======================================================================================================================

//======================================================================================================================
// Scala source file belonging to the org.facsim.util.log package.
//======================================================================================================================
package org.facsim.util.log

import org.facsim.util.LibResource
import scala.reflect.runtime.currentMirror
import scala.reflect.runtime.universe._

/** Base trait for all log message severity classifications.
 *
 *  @since 0.2
 */
sealed trait Severity
extends Ordered[Severity] {

  /** Name of this severity type.
   *
   *  @since 0.2
   */
  val name: String

  /** Abbreviated name of this severity type, typically used in log messages.
   *
   *  @since 0.2
   */
  val abbrName: String

  /** Rank of this severity.
   *
   *  The higher this value, the higher the severity of the associated message.
   */
  protected val severity: Int

  /** Compare this message severity instance to another instance.
   *
   *  @param that Message severity instance being compared to.
   *
   *  @return A value less than zero if this severity is less than `that` severity; zero if the two severities are
   *  equal; a value greater than zero if this severity is greater than the `that` severity.
   */
  final override def compare(that: Severity): Int = severity.compareTo(that.severity)
}

/** Debug log message classification.
 *
 *  Debug log messages, of which there may be many, can be utilized for detailed debug logging.
 *
 *  @since 0.2
 */
case object DebugSeverity
extends Severity {

  /** @inheritdoc */
  override val name: String = LibResource("log.DebugSeverityName")

  /** @inheritdoc */
  override val abbrName: String = LibResource("log.DebugSeverityAbbrName")

  /** @inheritdoc
   *
   *  @note Debug log messages have the lowest severity.
   */
  protected override val severity: Int = 0
}

/** Informational log message classification.
 *
 *  Informational log messages, typically used to document program state changes and operations.
 *
 *  @since 0.2
 */
case object InformationSeverity
extends Severity {

  /** @inheritdoc */
  override val name: String = LibResource("log.InformationSeverityName")

  /** @inheritdoc */
  override val abbrName: String = LibResource("log.InformationSeverityAbbrName")

  /** @inheritdoc
   *
   *  @note Information log messages have higher severity than [[DebugSeverity]] messages, but a lower severity than all
   *  others.
   */
  override val severity: Int = 1
}

/** Warning log message classification.
 *
 *  Warning log messages are typically used to document potential problems, unrecommended usage, etc..
 *
 *  @since 0.2
 */
case object WarningSeverity
extends Severity {

  /** @inheritdoc */
  override val name: String = LibResource("log.WarningSeverityName")

  /** @inheritdoc */
  override val abbrName: String = LibResource("log.WarningSeverityAbbrName")

  /** @inheritdoc
   *
   *  @note Warning log messages have higher severity than [[DebugSeverity]] and [[InformationSeverity]] messages, but a
   *  lower severity than all others.
   */
  protected override val severity: Int = 2
}

/** Important log message classification.
 *
 *  Important log messages are typically used to document significant program state changes and operations.
 *
 *  @since 0.2
 */
case object ImportantSeverity
extends Severity {

  /** @inheritdoc */
  override val name: String = LibResource("log.ImportantSeverityName")

  /** @inheritdoc */
  override val abbrName: String = LibResource("log.ImportantSeverityAbbrName")

  /** @inheritdoc
   *
   *  @note Important log messages have higher severity than all other messages, with the exception of errors.
   */
  protected override val severity: Int = 3
}

/** Error log message classification.
 *
 *  Error log messages are typically used to document errors that are non-fatal and recoverable. Such errors should not,
 *  byvthemselves, prevent the application from continuing execution
 *
 *  @since 0.2
 */
case object ErrorSeverity
extends Severity {

  /** @inheritdoc */
  override val name: String = LibResource("log.ErrorSeverityName")

  /** @inheritdoc */
  override val abbrName: String = LibResource("log.ErrorSeverityAbbrName")

  /** @inheritdoc
   *
   *  @note Error log messages have higher severity than all other messages, with the exception of [[FatalSeverity]]
   *  messages.
   */
  protected override val severity: Int = 4
}

/** Fatal error log message classification.
 *
 *  Fatal error log messages are typically used to document errors that are fatal and unrecoverable. They indicate that
 *  the application is about to exit and/or crash.
 *
 *  @since 0.2
 */
case object FatalSeverity
extends Severity {

  /** @inheritdoc */
  override val name: String = LibResource("log.FatalSeverityName")

  /** @inheritdoc */
  override val abbrName: String = LibResource("log.FatalSeverityAbbrName")

  /** @inheritdoc
   *
   *  @note Fatal error messages have higher severity than all other messages.
   */
  protected override val severity: Int = 5
}

/** Severity companion object.
 *
 *  @since 0.2
 */
object Severity {

  /** Set of all severity object classes.
   *
   *  @return Set of all severity objects.
   */
  private val severities: Set[Severity] = {

    // Get the symbol of the Severity trait. Since we cannot lookup direct known subclasses of a trait, we must then
    // convert it to a class.
    val cls = symbolOf[Severity].asClass

    // Now lookup all known sub-class instances. This gives us a set of Symbol instances for the above defined
    // subclasses of Severity. First, we must convert them to ClassSymbols. For safety, we then filter out any that are
    // not Scala "object" types (termed modules in the reflection library: a ModuleClassSymbol stores the type of the
    // Object as a subclass of ClassSymbol.)
    //
    // Next, we need to get the self type of the module class instance and convert this to a ModuleSymbol, which we
    // obtain via the type's "termSymbol" method.
    //
    // Finally, we turn the resulting ModuleSymbols back into the actual case object value instances.
    //
    // Phew!
    //
    // For more on ClassSymbols, ModuleClassSymbols, ModuleSymbols, Symbols, TermSymbols and TypeSymbols, refer to:
    //   https://docs.scala-lang.org/overviews/reflection/symbols-trees-types.html
    cls.knownDirectSubclasses
    .map(_.asClass)
    .filter(_.isModuleClass)
    .map(_.selfType.termSymbol.asModule)
    .map(ms => currentMirror.reflectModule(ms).instance.asInstanceOf[Severity]) //scalastyle:ignore token
  }

  /** Map of severity name to severity.
   *
   *  This is used to look up each severity by name.
   */
  private val severityDictionary = severities.map(s => (s.name, s)).toMap

  /** Retrieve a list of severities, ordered by rank.
   *
   *  @return List of severities, ordered from lowest rank to highest rank.
   *
   *  @since 0.2
   */
  def severityList: List[String] = severities.toList.sorted.map(_.name)

  /** Lookup a severity by name.
   *
   *  @todo Names are currently locale-specific. It might be better to register all names, in all locales, to each
   *  severity instance.
   *
   *  @param name Name of the severity to be retrieved.
   *
   *  @return Severity matching `name` wrapped-up in a `[[scala.Some Some]]`l or `[[scala.None None]]`.
   *
   *  @since 0.2
   */
  def withName(name: String): Option[Severity] = severityDictionary.get(name)
}