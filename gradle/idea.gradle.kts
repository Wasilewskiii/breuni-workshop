apply { plugin("idea") }

//idea {
//  project {
//    languageLevel = "1.8"
//    ipr.withXml { provider ->
//      provider.node.component.find { it.@ name == "VcsDirectoryMappings" }.mapping.@ vcs = "Git"
//    }
//  }
//  module {
//    iml {
//      withXml {
//        def moduleRoot = it . asNode ()
//        def facetManager = moduleRoot . component . find { component -> component."@name" == "FacetManager" }
//        if (!facetManager) {
//          facetManager = moduleRoot.appendNode("component", [name: "FacetManager"])
//        }
//
//        def springFacet = facetManager . facet . find { facet -> facet."@type" == "Spring" && facet."@name" == "Spring" }
//        if (!springFacet) {
//          springFacet = facetManager.appendNode("facet", [type: "Spring", name: "Spring"])
//          springFacet.appendNode("configuration")
//        }
//      }
//    }
//  }
//}
