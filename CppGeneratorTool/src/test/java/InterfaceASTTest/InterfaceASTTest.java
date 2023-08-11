package InterfaceASTTest;

import ClangAST.ClangAST;
import InterfaceAST.InterfaceAST;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InterfaceASTTest {
    @Test
    public void Test_InitFromJson()
    {
        String json = "{\"id\":\"0x2495bf0\",\"kind\":\"NamespaceDecl\",\"loc\":{\"offset\":62,\"file\":\"IFoo.h\",\"line\":5,\"col\":11,\"tokLen\":2},\"range\":{\"begin\":{\"offset\":52,\"col\":1,\"tokLen\":9},\"end\":{\"offset\":138,\"line\":13,\"col\":1,\"tokLen\":1}},\"previousDecl\":\"0x24956f0\",\"name\":\"fs\",\"originalNamespace\":{\"id\":\"0x24956f0\",\"kind\":\"NamespaceDecl\",\"name\":\"fs\"},\"inner\":[{\"id\":\"0x2495c60\",\"kind\":\"CXXRecordDecl\",\"loc\":{\"offset\":77,\"line\":8,\"col\":10,\"tokLen\":4},\"range\":{\"begin\":{\"offset\":71,\"col\":4,\"tokLen\":5},\"end\":{\"offset\":134,\"line\":11,\"col\":4,\"tokLen\":1}},\"name\":\"IFoo\",\"tagUsed\":\"class\",\"completeDefinition\":true,\"definitionData\":{\"canConstDefaultInit\":true,\"copyAssign\":{\"hasConstParam\":true,\"implicitHasConstParam\":true,\"nonTrivial\":true},\"copyCtor\":{\"hasConstParam\":true,\"implicitHasConstParam\":true,\"needsImplicit\":true,\"nonTrivial\":true,\"simple\":true},\"defaultCtor\":{\"defaultedIsConstexpr\":true,\"exists\":true,\"isConstexpr\":true,\"needsImplicit\":true,\"nonTrivial\":true},\"dtor\":{\"irrelevant\":true,\"simple\":true,\"trivial\":true},\"hasConstexprNonCopyMoveConstructor\":true,\"isAbstract\":true,\"isLiteral\":true,\"isPolymorphic\":true,\"moveAssign\":{\"exists\":true,\"nonTrivial\":true,\"simple\":true},\"moveCtor\":{\"exists\":true,\"needsImplicit\":true,\"nonTrivial\":true,\"simple\":true}},\"inner\":[{\"id\":\"0x2495d78\",\"kind\":\"CXXRecordDecl\",\"loc\":{\"offset\":77,\"line\":8,\"col\":10,\"tokLen\":4},\"range\":{\"begin\":{\"offset\":71,\"col\":4,\"tokLen\":5},\"end\":{\"offset\":77,\"col\":10,\"tokLen\":4}},\"isImplicit\":true,\"name\":\"IFoo\",\"tagUsed\":\"class\"},{\"id\":\"0x2495f38\",\"kind\":\"CXXMethodDecl\",\"loc\":{\"offset\":106,\"line\":10,\"col\":20,\"tokLen\":9},\"range\":{\"begin\":{\"offset\":93,\"col\":7,\"tokLen\":7},\"end\":{\"offset\":128,\"col\":42,\"tokLen\":1}},\"name\":\"returnBar\",\"mangledName\":\"_ZN2fs4IFoo9returnBarERNS_3BarE\",\"type\":{\"qualType\":\"fs::Bar&(fs::Bar&)\"},\"virtual\":true,\"pure\":true,\"inner\":[{\"id\":\"0x2495e48\",\"kind\":\"ParmVarDecl\",\"loc\":{\"offset\":121,\"col\":35,\"tokLen\":3},\"range\":{\"begin\":{\"offset\":116,\"col\":30,\"tokLen\":3},\"end\":{\"offset\":121,\"col\":35,\"tokLen\":3}},\"name\":\"bar\",\"mangledName\":\"_ZZN2fs4IFoo9returnBarERNS_3BarEE3bar\",\"type\":{\"qualType\":\"fs::Bar&\"}}]},{\"id\":\"0x2496038\",\"kind\":\"CXXMethodDecl\",\"loc\":{\"offset\":77,\"line\":8,\"col\":10,\"tokLen\":4},\"range\":{\"begin\":{\"offset\":77,\"col\":10,\"tokLen\":4},\"end\":{\"offset\":77,\"col\":10,\"tokLen\":4}},\"isImplicit\":true,\"name\":\"operator=\",\"mangledName\":\"_ZN2fs4IFooaSERKS0_\",\"type\":{\"qualType\":\"fs::IFoo&(constfs::IFoo&)\"},\"inline\":true,\"constexpr\":true,\"explicitlyDefaulted\":\"default\",\"inner\":[{\"id\":\"0x2496148\",\"kind\":\"ParmVarDecl\",\"loc\":{\"offset\":77,\"col\":10,\"tokLen\":4},\"range\":{\"begin\":{\"offset\":77,\"col\":10,\"tokLen\":4},\"end\":{\"offset\":77,\"col\":10,\"tokLen\":4}},\"type\":{\"qualType\":\"constfs::IFoo&\"}}]},{\"id\":\"0x24961e8\",\"kind\":\"CXXMethodDecl\",\"loc\":{\"offset\":77,\"col\":10,\"tokLen\":4},\"range\":{\"begin\":{\"offset\":77,\"col\":10,\"tokLen\":4},\"end\":{\"offset\":77,\"col\":10,\"tokLen\":4}},\"isImplicit\":true,\"name\":\"operator=\",\"mangledName\":\"_ZN2fs4IFooaSEOS0_\",\"type\":{\"qualType\":\"fs::IFoo&(fs::IFoo&&)\"},\"inline\":true,\"constexpr\":true,\"explicitlyDefaulted\":\"default\",\"inner\":[{\"id\":\"0x24962f8\",\"kind\":\"ParmVarDecl\",\"loc\":{\"offset\":77,\"col\":10,\"tokLen\":4},\"range\":{\"begin\":{\"offset\":77,\"col\":10,\"tokLen\":4},\"end\":{\"offset\":77,\"col\":10,\"tokLen\":4}},\"type\":{\"qualType\":\"fs::IFoo&&\"}}]},{\"id\":\"0x2496380\",\"kind\":\"CXXDestructorDecl\",\"loc\":{\"offset\":77,\"col\":10,\"tokLen\":4},\"range\":{\"begin\":{\"offset\":77,\"col\":10,\"tokLen\":4},\"end\":{\"offset\":77,\"col\":10,\"tokLen\":4}},\"isImplicit\":true,\"name\":\"~IFoo\",\"mangledName\":\"_ZN2fs4IFooD1Ev\",\"type\":{\"qualType\":\"void()\"},\"inline\":true,\"explicitlyDefaulted\":\"default\"}]}],\"includes\":[\"\\\"../include/IFoo.h\\\"\"]}";

        ClangAST clangAST = new ClangAST().parse(json);
        InterfaceAST sut = new InterfaceAST(clangAST);

        assertEquals(sut.file.namespace, "fs");
    }
}
