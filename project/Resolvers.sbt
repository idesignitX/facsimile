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
// You should have received a copy of the GNU Lesser General Public License along with Facsimile. If not,
// see http://www.gnu.org/licenses/lgpl.
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
// SBT file to include third-party repositories required by the build.
//
// Note: Resolvers of the form:
//
//   "some repo" at "https://some.repo.url/"
//   Resolver.url("some repo", url("https://some.repo.url/"))(Resolver.mavenStylePatterns)
//
// identify a Maven-formatted repository. If the repository is an Ivy repository, then the following form is required:
//
//   Resolver.url("some repo", url("https://some.repo.url/"))(Resolver.ivyStylePatterns)
//
// Other options are available too. Refer to the following link for further information:
//
//   https://www.scala-sbt.org/1.x/docs/Resolvers.html
//
// NOTE: In some cases, it may be necessary to add the same resolvers to the Facsimile.sbt build file. Refer to the
// resolver notes in that file for further details.
//======================================================================================================================

// Artima repository is required for SuperSafe compiler plugin.
ThisBuild / resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"