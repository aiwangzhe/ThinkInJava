// Generated from /home/wangzhe/IdeaProjects/ThinkInJava/Antlr4/src/main/java/structures/Comment.g4 by ANTLR 4.9.1
package structures;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link CommentVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class CommentBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements CommentVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitFile(CommentParser.FileContext ctx) { return visitChildren(ctx); }
}