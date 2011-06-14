// HTMLParser Library $Name:  $ - A java-based parser for HTML
// http://sourceforge.org/projects/htmlparser
// Copyright (C) 2003 Derrick Oswald
//
// Revision Control Information
//
// $Source: /cvsroot/htmlparser/htmlparser/resources/HtmlTaglet.java,v $
// $Author: derrickoswald $
// $Date: 2003/12/16 02:29:56 $
// $Revision: 1.1 $
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

import com.sun.tools.doclets.Taglet;
import com.sun.javadoc.*;
import java.util.Map;
import org.htmlparser.util.Translate;

/**
 * A JavaDoc Taglet that encodes HTML.
 * This inline <A href="http://java.sun.com/j2se/1.4.2/docs/tooldocs/javadoc/taglet/com/sun/tools/doclets/Taglet.html">taglet</A>
 * converts HTML into character references for embedding into
 * <A href="http://java.sun.com/j2se/1.4.2/docs/tooldocs/javadoc/index.html">Javadocs</A>.
 * For example, it converts &lt;html&gt; into &amp;lt;html&amp;gt;.
 * Typical usage is to embed an example stream of html into the javadoc for
 * a class or method:
 * <pre><font color="green">
 * /**
 *  * Gather DIV elements.
 *  * This method takes &#123;@.html &lt;div&gt;&lt;/div&gt;&#125; pairs and
 *  * constructs nested ...
 * </font></pre>
 * This is useful so that the documentation is readable while coding and when
 * presented in the generated javadocs. Normally, embedding HTML in the
 * javadoc documentation requires the use of character entity references,
 * otherwise the HTML is interpreted by the javadoc tool and is stripped out.
 * The programmer can manually embed the character translations to pass the
 * HTML through, but the resultant comment is extremely hard to read and
 * understand when editing the code directly. Plus there is the added
 * possibility of an incorrect encoding because of the manual step.<p>
 * The use of this taglet requires a 1.4.x or higher JDK, but it is not
 * expected that users with older JDKs will be generating javadocs when they
 * are provided in the distribution.<p>
 * The name was supposed to be "html", but a warning message is generated
 * by the javadoc tool if a custom tag name doesn't contain any dots. So the
 * next best name ".html" is used instead, with a passing resemblance to
 * directives in nroff.
 */
public class HtmlTaglet implements Taglet
{
    private static final String NAME = ".html";

    /**
     * Construct a taglet for encoding HTML in doc comments.
     */
    public HtmlTaglet ()
    {
    }
    
    /**
     * Return the name of this custom taglet.
     */
    public String getName ()
    {
        return (NAME);
    }
    
    /**
     * Will return true since <code>&#123;@.html&#125;</code>
     * can be used in field documentation.
     * @return <code>true</code> since <code>&#123;@.html&#125;</code>
     * can be used in field documentation.
     */
    public boolean inField ()
    {
        return (true);
    }
    
    /**
     * Will return true since <code>&#123;@.html&#125;</code>
     * can be used in constructor documentation.
     * @return <code>true</code> since <code>&#123;@.html&#125;</code>
     * can be used in constructor documentation.
     */
    public boolean inConstructor ()
    {
        return (true);
    }
    
    /**
     * Will return true since <code>&#123;@.html&#125;</code>
     * can be used in method documentation.
     * @return <code>true</code> since <code>&#123;@.html&#125;</code>
     * can be used in method documentation.
     */
    public boolean inMethod ()
    {
        return (true);
    }
    
    /**
     * Will return true since <code>&#123;@.html&#125;</code>
     * can be used in method documentation.
     * @return <code>true</code> since <code>&#123;@.html&#125;</code>
     * can be used in overview documentation.
     */
    public boolean inOverview ()
    {
        return (true);
    }
    
    /**
     * Will return true since <code>&#123;@.html&#125;</code>
     * can be used in package documentation.
     * @return <code>true</code> since <code>&#123;@.html&#125;</code>
     * can be used in package documentation.
     */
    public boolean inPackage ()
    {
        return (true);
    }
    
    /**
     * Will return true since <code>&#123;@.html&#125;</code>
     * can be used in type documentation (classes or interfaces).
     * @return true since <code>&#123;@.html&#125;</code>
     * can be used in type documentation.
     */
    public boolean inType ()
    {
        return (true);
    }
    
    /**
     * Will return true since <code>&#123;@.html&#125;</code>
     * is an inline tag.
     * @return <code>true</code> since <code>&#123;@.html&#125;</code>
     * is an inline tag.
     */
    
    public boolean isInlineTag ()
    {
        return (true);
    }
    
    /**
     * Register this Taglet.
     * @param tagletMap  the map to register this tag to.
     */
    public static void register (Map tagletMap)
    {
        HtmlTaglet tag = new HtmlTaglet ();
        tagletMap.put (tag.getName (), tag);
    }

    /**
     * Format the given string to appear "as is" within a JavaDoc comment.
     * This method is more complicated than it needs to be, since you might
     * say why not just use PRE tags surrounding the text. Unfortunately, PRE
     * is a block level tag that breaks the flow of text, preventing inline
     * operation. Instead we manually format the whitespace (actually just
     * spaces and newlines) within the string to preserve the format.
     */
    protected String format (String s)
    {
        int base;
        int offset;
        StringBuffer ret;

        ret = new StringBuffer (512);
        
        base = 0;
        offset = 0;
        while (-1 != (offset = s.indexOf ('\n', base)))
        {
            ret.append (Translate.encode (s.substring (base, offset)));
            ret.append ("<br>\n");
            base = offset + 1;
        }
        if (base != s.length ())
            ret.append (Translate.encode (s.substring (base)));
        
        s = ret.toString ();
        ret.setLength (0);
        for (int i = 0; i < s.length (); i++)
            if (' ' == s.charAt (i))
                ret.append ("&nbsp;");
            else
                ret.append (s.charAt (i));
            
        return (ret.toString ());
    }

    /**
     * Given the <code>Tag</code> representation of this custom
     * tag, return its string representation.
     * @param tag   the <code>Tag</code> representation of this custom tag.
     */
    public String toString (Tag tag)
    {
        return (format (tag.text ()));
    }
    
    /**
     * Given an array of <code>Tag</code>s representing this custom
     * tag, return its string representation.
     * @param tags  the array of <code>Tag</code>s representing of this custom tag.
     */
    public String toString(Tag[] tags)
    {
        StringBuffer ret;

        if (0 == tags.length)
            return (null);
        else
        {
            ret = new StringBuffer (512);
            for (int i = 0; i < tags.length; i++)
            {
                if (i > 0)
                    ret.append ("<br>\n");
                ret.append (format (tags[i].text()));
            }
            return (ret.toString ());
        }
    }
}