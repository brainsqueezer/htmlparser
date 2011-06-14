HTMLParser Version 1.6 (Release Build Jun 10, 2006)
*********************************************

Contents of the distribution
----------------------------
  (i) jar files - lib directory
      HTML Parser jars: htmlparser.jar, htmllexer.jar, thumbelina.jar and
      filterbuilder.jar.
      Also thirdparty jar files sax2.jar and junit.jar.

 (ii) source code - src.zip
      Also contains necessary resources, and build file. Unzip this
      and you should be all set to build the parser from its source.
      You would need Jakarta Ant installed.
    	 
(iii) documentation - docs directory (includes javadocs)
      Point your browser at index.html in the docs directory.
    
 (iv) executing scripts - bin directory
      Batch/script files assume that Java is visible in your path.
      Most require Java 1.2 (or upwards), except for lexer.

  (v) license.txt (GNU Lesser General Public License)

 (vi) this file, readme.txt

Changes since Version 1.5
-------------------------

New Functionality
-----------------
    Support has been added for commonly requested composite tags, P and H1-H6.
    Definition list tags (dl, dt, dd), are also now included in the standard
    set of tags recognized by the parser.
    The FilterBean now has a 'recursive' property to control descent through
    children when applying filters.
    The NodeList class is a little more standard now with a remove(node) method.
    The Node interface has been augmented with get first/last child and
    get previous/next sibling methods to ease traversing the HTML document.
    The TextNode class has an added isWhiteSpace method that returns true
    when it contains no printable characters.
    NodeTreeWalker, a utility class to traverse a tree of Node objects using
    either depth-first or breadth-first tree order has been added.
    An XorFilter has been added to round out our NOT, AND and OR filters,
    along with new constructors to OrFilter/AndFilter that take an array of
    NodeFilter's.
    Deflate encoding is now handled correctly and there is now an option to
    have the ConnectionManager follow redirections manually so that cookie
    processing can occur between redirections.
    There is a new override for toHtml() that avoids issuing generated end tags.

Refactoring
-----------
    Some refactoring to allow the htmllexer jar file to be compiled by gcj.
    Moved non-JUnit test code to Request For Enhancement (RFE) as attachments,
    so all the code in the tests package should now compile.
    Removed all deprecated classes and methods.

Bug Fixes
---------
#1496863 StringBean collapse() adds extra whitespace
#1488951 RemarkNode.toPlainTextString() incorrect behaviour
#1467712 Page#getCharset never works
#1461473 Relative links starting with ?
#1457371 Script tag consumes too much from document being parsed
#1445795 return as TextNode when processing jsp
#1445309 XML processing instructions are returned as text
#1376851 Null-valued cookies cause exception
#1375230 some javascript breaks stringbean
#1345049 HTMLParser should not terminate a comment with --->
#1344687 A bug when set cookies
#1334408 Exception occurs based on string length
#1322686 when illegal charset specified
#1227213 Particular SCRIPT tags close too late

Patches
-------
#1436082 Follow redirections with cookie processing
#1338534 Support get first/last child, previous/next sibling

Requests For Enhancements
------------
#1394144 handle deflate encoding

Changes since Version 1.4
-------------------------
New APIs
    Implement rudimentary sax parser. Currently exposes DOM parser via sax project 
    A new http package is added, the primary class being Connectionmanager which
    handles proxies, passwords and cookies. Some testing still needed.
    Also removed some line separator cruft.
    Added parseCDATA to the Lexer, used in script and style scanners.
    Note that this is significantly new behaviour that now adheres to appendix
    B.3.2 Specifying non-HTML data  of the HTML reference:
    http://www.w3.org/TR/html4/appendix/notes.html#notes-specifying-data
Configuration Management
    Removed the need for the Translate class to be packaged with htmllexer.jar.
    This results in a lighter weight component.
    Updated the logo and included the LGPL license.
    Fixed the Windows batch files.
    Added optional "classes" property to build.xml. This directory is where
    class files are put. It defaults to src.
	To use:
	    ant -Dclasses=classdir <target>
	where classdir is/will-be a peer directory to src.
    Fixed various end user experience issues.
Refactoring
    Added static STRICT flag to ScriptScanner to revert to legacy handling of
    broken ETAGO (</). If STRICT is true, scan according to HTML specification,
    else if false, scan with quote smart state machine which heuristically
    yields the correct parse.
    Obviated LinkProcessor and moved it's functionality to the Page class.
    Added Tag, Text and Remark interfaces and moved concrete node
    implementations to the nodes package, removing the lexer.nodes package.
    Most internals now use the Tag interface.
    Removed the org.htmlparser.tags.Tag class and moved the remaining (minor)
    functionality to the TagNode class.
    So now tags inherit directly from TagNode or CompositeTag.
	** NOTE: If you have subclassed org.htmlparser.tags.Tag, use org.htmlparser.nodes.TagNode now.**
    Removed deprecated methods getTagBegin/getTagEnd and deleted unused classes:
        PeekingIterator and it's Implementation.
    Added ObjectTag (like an applet tag).
    Added a real StringSource that reads directly from a String rather than
    creating a byte array. This avoids character encoding losses.
    Incorporate patch #1004985 Page.java, by making getCharset() and findCharset() static.
    Incorporated some speed optimizations based on profiling.
    Deprecated node decorators.
Filters
    Added CssSelectorNodeFilter and RegExFilter.
    Added the filter builder tool.
    Added link pattern filters LinkRegexFilter and LinkStringFilter.
    
Enhancement Requests
--------------------
1160345 NodeList.visitAllNodesWith
1017249	HTML Client Doesn't Support Cookies but will follow redirect
1010586	Add support for password protected URL
1000739	Add support for proxy scenario
1000063 FilterBean
943593 LinkProcessor.extract(link,base) weird behaviour?
943197 Accept gzip / deflate content encodings
874000 Remove specialized tag signatures from NodeVisitor

Bug Fixes
---------
1161137 Non English Character web page
1160010 NullPointerException in addCookies
1153508 CVS sources do not compile
1121401 No Parsing with yahoo!
1104627 Parser Crash reading javascript
1061869 Crashing when trying to capture link to XLS document
1056438 Byte Order Mark
1044707 mark()/reset() issues
1024045 StringBean crashes on an URL
1021925 StyleTag with missing linefeed prevents page from parsing
1018884 'compile' ant task from build.xml messes up ./src directory
1005409 Input file not free by parser.
998195 SiteCatpurer just crashed
995703 Parser Crash
988846 Linkbean getLinks() segmentation fault (duplicate of above)
973137 Double-bytes characters are messed after parsing
936392 ScriptTag visitor fails for comments with '
919738 Text has not been extracted correctly using StringBean

Acknowledgements
----------------
The following people have contributed important bug reports, feature ideas :
[1] Kaarle Kaaila
[2] Taras Bendik
[3] Allen G Fogelson
[4] Manpreet Singh
[5] Roger Kjensrud
[6] Nash Tsai
[7] Rodney S Foley
[8] Serge Kruppa
[9] Raj Sharma
[10] Sam Joseph
[11] Raghavender Srimantula
[12] Wolfgang Germund
[13] Claude Duguay
[14] Cedric Rosa
[15] Amit Rana
[16] Kamen
[17] John Zook
[18] Mazlan Mat
[19] Rob Shields
[20] Dhaval Udani
[21] Joe Ryburn
[22] Domenico Lordi
[23] Stephen Harrington
[24] Derrick Oswald
[25] Joshua Kerievsky
[26] Stephen Nightingale
[27] Donnla Nic Gearailt
[28] Pim Schrama
[29] Nick Burch
[30] Gernot Fricke
[31] Anthony Labarre
[32] Alberto Nacher
[33] Rogers George
[34] Jon Gillette
[35] Enrico Triolo
[36] Dave Anderson
[37] Keiron McCammon
[38] Martin Hudson
[39] Matthew Buckett
[40] Jamie McCrindle
[41] John Derrick
[42] Ian MacFarlane

If you find any bugs, please go to 
http://htmlparser.sourceforge.net and click on the Bugs link. You can open a bug case here. 
You will be amazed at the speed of fixing. Open Source rocks!

And please join the HTMLParser-User mailing list
to get help on getting started. Join HTMLParser-Announce to 
be notified whenever a new release is out.

All these mailing lists can be joined from http://htmlparser.sourceforge.net
