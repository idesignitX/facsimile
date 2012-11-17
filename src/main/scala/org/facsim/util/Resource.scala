/*
Facsimile -- A Discrete-Event Simulation Library
Copyright © 2004-2012, Michael J Allen.

This file is part of Facsimile.

Facsimile is free software: you can redistribute it and/or modify it under the
terms of the GNU Lesser General Public License as published by the Free
Software Foundation, either version 3 of the License, or (at your option) any
later version.

Facsimile is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more
details.

You should have received a copy of the GNU Lesser General Public License along
with Facsimile.  If not, see http://www.gnu.org/licenses/lgpl.

The developers welcome all comments, suggestions and offers of assistance.  For
further information, please visit the project home page at:

  http://facsim.org/

Thank you for your interest in the Facsimile project!

IMPORTANT NOTE: All patches (modifications to existing files and/or the
addition of new files) submitted for inclusion as part of the official
Facsimile code base, must comply with the published Facsimile Coding Standards.
If your code fails to comply with the standard, then your patches will be
rejected.  For further information, please visit the coding standards at:

  http://facsim.org/Documentation/CodingStandards/
===============================================================================
Scala source file from the org.facsim.util package.
*/
//=============================================================================

package org.facsim.util

import java.net.URL
import java.text.MessageFormat
import java.util.MissingResourceException
import java.util.ResourceBundle
import javax.swing.ImageIcon
import scala.swing.Swing
import org.facsim.requireNonNull

//=============================================================================
/**
Provides access to a locale-specific resource bundle belonging to a library or
application.

Further information on how locale-specific resources are identified is at
[[java.util.ResourceBundle!]].

The user's current preferred locale is identified through a call to
[[java.util.Locale$.getDefault()]].

@todo Add code to retrieve package files, including images, manifests, etc.

@constructor Retrieve the resource bundle with the specified name.

@param bundleName Identifies the base name of the resource bundle to be loaded.

@throws java.lang.NullPointerException if '''bundleName''' is `null`.

@throws java.util.MissingResourceException if a resource bundle with base name
'''bundleName''' cannot be found.

@see [[java.util.ResourceBundle!]] for information on Java resource bundles.

@see [[java.util.Locale!]] for information on Java locale processing.

@since 0.0
*/
//=============================================================================

class Resource (bundleName: String) {

/*
Verify that we have a bundle name.
*/

  requireNonNull ("bundleName", bundleName)

/**
Resource bundle.

The locale of the bundle defaults to the JVM's default locale; if a
locale-specific resource bundle matching the default locale is available, then
it will be loaded - otherwise, the "en_US" (United States English) resource
bundle will be loaded.
*/

  private final val bundle = ResourceBundle.getBundle (bundleName)
  assert (bundle ne null)

//-----------------------------------------------------------------------------
/**
Retrieve and format a locale-specific string resource.

Retrieves the string resource corresponding to the supplied key from the
application resource bundle.  Optional arguments are used to populate the
formatted version of the resulting string, which will be in the locale that
best matches the user's preference.

@param key Key used to identify the string resource to be retrieved.

@param arguments Arguments to be merged into, and formatted as part of, the
resulting string resource.  May be omitted if no arguments are required.

@return Locale-specific, formatted version of the requested string resource.

@throws java.lang.NullPointerException if '''key''' is `null`.

@throws java.util.MissingResourceException if there is no string resource
indexed by '''key'''.

@throws java.lang.ClassCastException if the resource indexed by '''key''' is
not a string.

@throws java.lang.IllegalArgumentException if the retrieved string is invalid
or if the '''arguments''' supplied are of the wrong type for the corresponding
format elements in the retrieved string.

@since 0.0
*/
//-----------------------------------------------------------------------------

  final def format (key: String, arguments: Any*) = {

/*
To convert a Scala vararg to a Java vararg, while boxing numbers requires this
rather ugly code...
*/

    MessageFormat.format (bundle.getString (key), arguments.map (_.asInstanceOf
    [AnyRef]): _*)
  }
}