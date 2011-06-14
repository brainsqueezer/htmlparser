// HTMLParser Library $Name:  $ - A java-based parser for HTML
// http://sourceforge.org/projects/htmlparser
// Copyright (C) 2004 Somik Raha
//
// Revision Control Information
//
// $Source: /cvsroot/htmlparser/htmlparser/src/org/htmlparser/tests/visitorsTests/LinkFindingVisitorTest.java,v $
// $Author: derrickoswald $
// $Date: 2004/01/02 16:24:57 $
// $Revision: 1.15 $
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
//

package org.htmlparser.tests.visitorsTests;

import org.htmlparser.tests.ParserTestCase;
import org.htmlparser.visitors.LinkFindingVisitor;

public class LinkFindingVisitorTest extends ParserTestCase {

    static
    {
        System.setProperty ("org.htmlparser.tests.visitorsTests.LinkFindingVisitorTest", "LinkFindingVisitorTest");
    }

    private String html =
        "<HTML><HEAD><TITLE>This is the Title</TITLE></HEAD><BODY>Hello World, <A href=\"http://www.industriallogic.com\">Industrial Logic</a></BODY></HTML>";

    public LinkFindingVisitorTest(String name) {
        super(name);
    }

    public void testLinkFoundCorrectly() throws Exception {
        createParser(html);
        LinkFindingVisitor visitor = new LinkFindingVisitor("Industrial Logic");
        parser.visitAllNodesWith(visitor);
        assertTrue("Found Industrial Logic Link",visitor.linkTextFound());
        assertEquals("Link Count",1,visitor.getCount());
    }

}
