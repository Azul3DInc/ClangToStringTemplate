package ClangAST;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.List;

public class ClangAST
{
    ClangNode root;

    public ClangNode getRoot()
    {
        return root;
    }

    public class ClangNode
    {
        String id;
        String kind;
        String name;
        String tagUsed;
        String access;
        ArrayList<ClangNode> inner;
        Type type;
        boolean virtual;
        boolean pure;
        ArrayList<String> includes;

        public String getId()
        {
            return id;
        }

        public String getKind()
        {
            return kind;
        }

        public String getName()
        {
            return name;
        }

        public String getTagUsed()
        {
            return tagUsed;
        }

        public String getAccess() { return access; }

        public Iterator<ClangNode> getInnerIterator()
        {
            if (inner != null)
            {
                return inner.iterator();
            }
            return Collections.emptyIterator();
        }

        public String getQualType()
        {
            if (type != null)
            {
                return type.qualType;
            }
            return "";
        }

        public boolean getVirtual()
        {
            return virtual;
        }

        public boolean getPure()
        {
            return pure;
        }

        public ArrayList<String> getIncludes()
        {
            return includes;
        }


        public class Type
        {
            String qualType;

            @Override
            public String toString()
            {
                return "Type{ qualType='" + qualType + '\'' + '}';
            }
        }

        @Override
        public String toString()
        {
            return "ClangNode{" + '\n' +
                    "id='" + id + '\'' +
                    ", kind='" + kind + '\'' +
                    ", name='" + name + '\'' +
                    ", tagUsed='" + tagUsed + '\'' +
                    ", inner=[\n" + inner + "\n]" +
                    ", type=" + type +
                    ", virtual=" + virtual +
                    ", pure=" + pure + '\n' +
                    ", includes=" + includes + '\n' +
                    '}';
        }
    }

    @Override
    public String toString()
    {
        return "ClangAST{" +
                root +
                '}';
    }

    public static ClangAST parse(String json)
    {
        return new Gson().fromJson("{\"root\": " + json + "}", ClangAST.class);
    }
}
