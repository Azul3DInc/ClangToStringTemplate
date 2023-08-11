package Utils;

public final class Utils
{
    private Utils()
    {
        throw new AssertionError("Static class Utils should not be instantiated.");
    }

    public static String removeConst(String type)
    {
        return type.replaceAll("\\bconst\\b", "").trim();
    }

    public static String removeAllRef(String type)
    {
        return type.replaceAll("&", "").trim();
    }

    public static String removeParametersFromQualType(String type)
    {
        int lastOpenParenthesesIndex = type.lastIndexOf('(');
        int lastClosedParenthesesIndex = type.lastIndexOf(')');

        if (lastOpenParenthesesIndex != -1 && lastClosedParenthesesIndex != -1 && lastClosedParenthesesIndex > lastOpenParenthesesIndex)
        {
            return (type.substring(0, lastOpenParenthesesIndex) + type.substring(lastClosedParenthesesIndex + 1)).trim();
        }

        return type;
    }

    public static String removeRightmostRef(String type)
    {
        int index = type.lastIndexOf('&');

        if (index != -1)
        {
            return (type.substring(0, index) + type.substring(index + 1, type.length())).trim();
        }

        return type;
    }

    public static String removeNamespaceScopeResolution(String type, String namespace)
    {
        if (type.startsWith(namespace + "::"))
        {
            return type.substring(namespace.length() + 2);
        }

        else if (type.contains("::" + namespace + "::"))
        {
            int startIndex = type.lastIndexOf("::" + namespace + "::") + 2; // +2 to include the last "::"
            return type.substring(startIndex);
        }

        return type;
    }

}
