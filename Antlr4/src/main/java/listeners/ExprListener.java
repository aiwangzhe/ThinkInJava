// Generated from /home/wangzhe/IdeaProjects/ThinkInJava/Antlr4/src/main/java/listeners/Expr.g4 by ANTLR 4.9.1
package listeners;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void enterS(ExprParser.SContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void exitS(ExprParser.SContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterE(ExprParser.EContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitE(ExprParser.EContext ctx);
}