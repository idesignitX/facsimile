/*
Facsimile -- A Discrete-Event Simulation Library
Copyright © 2004-2013, Michael J Allen.

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
Scala source file from the org.facsim.anim.cell package.
*/
//=============================================================================

package org.facsim.anim.cell

import scalafx.scene.paint.Material

//=============================================================================
/**
Trait defining the attribute access functions of a cell.

@since 0.0
*/
//=============================================================================

private [cell] trait CellAttributes {

//-----------------------------------------------------------------------------
/**
Face color.

@return Face color of the cell, as a ''ScalaFX''
[[scalafx.scene.paint.Material!]] or `None` if a face color is not defined.

@see [[http://facsim.org/Documentation/Resources/AutoModCellFile/Colors.html
Face & Edge Colors]]

@since 0.0
*/
//-----------------------------------------------------------------------------

  def faceColor: Option [Material]

//-----------------------------------------------------------------------------
/**
Edge color.

@note In ''VR Graphics'' mode (available since ''[[http://www.automod.com/
AutoMod®]]'' version 10.0), edge color in ''cell'' files is ignored, with face
color being used instead.  ''Facsimile'' honors edge color as far as possible,
using it to render cell's if the cell employs ''wireframe'' rendering.

@return Edge color of the cell, as a ''ScalaFX''
[[scalafx.scene.paint.Material!]] or `None` if an edge color is not defined.

@see [[http://facsim.org/Documentation/Resources/AutoModCellFile/Colors.html
Face & Edge Colors]]

@since 0.0
*/
//-----------------------------------------------------------------------------

  def edgeColor: Option [Material]

//-----------------------------------------------------------------------------
/**
Line style.

@return Line style of the cell.

@see [[http://facsim.org/Documentation/Resources/AutoModCellFile/LineStyle.html
Line Styles]]

@since 0.0
*/
//-----------------------------------------------------------------------------

  def lineStyle: LineStyle.Value

//-----------------------------------------------------------------------------
/**
Line width, in pixels.

@return Line width of the cell in pixels.

@see [[http://facsim.org/Documentation/Resources/AutoModCellFile/LineWidth.html
Line Width]]

@since 0.0
*/
//-----------------------------------------------------------------------------

  def lineWidth: LineWidth

//-----------------------------------------------------------------------------
/**
Display style.

@return Display style of the cell.

@see
[[http://facsim.org/Documentation/Resources/AutoModCellFile/DisplayStyle.html
Display Styles]]

@since 0.0
 */
//-----------------------------------------------------------------------------

  def displayStyle: DisplayStyle.Value

//-----------------------------------------------------------------------------
/**
Name.

@return name of this cell, or `None` if no name is defined.

@see [[http://facsim.org/Documentation/Resources/AutoModCellFile/Name.html Cell
Names]]

@since 0.0
*/
//-----------------------------------------------------------------------------

  def name: Option [String]
}