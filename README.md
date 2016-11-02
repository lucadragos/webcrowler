Wiprodigital webcrawler - Dragos Luca
> My thoughts:
I build this small application with an accent on these 3 things in mind: functionality, quality and flexibility. As per
design i didn't want to over engineer but i also wanted to have the flexibility to add new features if required.

> How i imaged the solution
About the problem to solve from a high level point i am seeing the sitemap as a tree which i need to discover
 and then to display or render or save in some kind of format. I used an iterative Breadth First Search algorithm to
 discover the map and to display a recursive walk of the tree.
 For flexibility each of the map operations discovery or rendering of the map is done in a pluggable manner to make
 room for the future to support new rendering or discovering functionality.

> How to run:
- the executable jar is at jar/ inside the parent directory
- you can run with java -jar webcrowler-1.0-SNAPSHOT.jar

> How to build
- as a build tool i used Gradle to be able to build application you need to have Gradle installed
- once you have Gradle install in the main directory just run: gradle build
- gradle build task will compile all the sources, run the unit tests and it will also create the fat jar

> The design:
I thing the design is simple and powerfull created under the SOLID principles. All the dependency injection
is done in the classical way through the constructors.

Sitemap package:
The state of the application is found in the "sitemap" package.
In here TNode is the node from which the tree is built with mininum validation at the constructor leve.
The SiteMap is the class which can perform map operations ( discover and render ).

Operations package:
Each sitemap operation is define in this package and implements the MapOperation interface. The 2 operations
implemented are DiscoverMapOperation and RenderSiteMap.
The DiscoveryMapOperation is just a iterative BFS walk which uses WiproFindLinksParser based on Jsout library
For RenderSiteMap i pushed this class a little bit to display the map on the console or to render it into a file but
both operations can stand alone. This could be a good next step to refactor.

Parser package:
The WiproFindLinksParser is highly static specialized class which uses the Jsoup library to perform html parsing
related to the wipro digital website. Is static tool mostly for convenience and due to the time constraint.
I see the maturity of this class using a set of properties and a set of rules in deciding what information to
extract.

Main package
The WebCrawlerMain is the application entry point. For convenience the wipro url is not received as a parameter
but it can be easily refactor to do so.

>> POSSIBLE NEXT FEATURES
-> we can add the functionality to stop the map discovery to a certain level. As an example we want to see only
the top 2 levels of the sitemap.
-> very easy we can add a wide range of map operations like: saving to an xml, saving to a json file, saving to a database
 or to generate the path of a node from the root
-> By adding an interface and state to the WiproFindLinksParser we can extend the input functionality from other sources
 or to use other parsing libraries

