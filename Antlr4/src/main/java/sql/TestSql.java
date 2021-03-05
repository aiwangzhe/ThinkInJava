package sql;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class TestSql {

    public static void main(String[] args) throws IOException {
        ANTLRInputStream inputStream = new ANTLRInputStream(System.in);
        SqlBaseLexer lexer = new SqlBaseLexer(inputStream);
        CommonTokenStream stream = new CommonTokenStream(lexer);
        SqlBaseParser parser = new SqlBaseParser(stream);
        ParseTree parseTree = parser.statement();
        ParseTree queryStatement = parser.querySpecification();
        Trees.inspect(parseTree, parser);
        ParseTreeWalker walker = new ParseTreeWalker();
        //walker.walk(new SqlBaseBaseListener(), parseTree);
        SqlBaseBaseVisitor visitor = new SqlBaseBaseVisitor();
        visitor.visit(parseTree);
    }
}
