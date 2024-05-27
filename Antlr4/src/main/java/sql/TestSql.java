package sql;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class TestSql {

    public static void main(String[] args) throws IOException {
        String command = "SELECT id,name,avg(sex) FROM db.test WHERE id > 10";
        SqlBaseLexer lexer = new SqlBaseLexer(CharStreams.fromString(command));
        CommonTokenStream stream = new CommonTokenStream(lexer);
        SqlBaseParser parser = new SqlBaseParser(stream);
        ParseTree parseTree = parser.statement();
        ParseTree queryStatement = parser.querySpecification();
        System.out.println(parseTree.toStringTree(parser));
        //Trees.inspect(parseTree, parser);
        ParseTreeWalker walker = new ParseTreeWalker();
        //walker.walk(new SqlBaseBaseListener(), parseTree);
        SqlBaseBaseVisitor visitor = new SqlBaseBaseVisitor();
        visitor.visit(parseTree);
    }
}
