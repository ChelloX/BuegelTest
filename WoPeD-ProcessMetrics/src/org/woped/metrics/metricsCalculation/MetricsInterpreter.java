package org.woped.metrics.metricsCalculation;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.woped.metrics.formalGrammar.metricsGrammarLexer;
import org.woped.metrics.formalGrammar.metricsGrammarParser;

public class MetricsInterpreter {
	
	public static double interpretString(String formula, MetricsCalculator mc){
		CharStream stream =
			new ANTLRStringStream(formula);
		metricsGrammarLexer lexer = new metricsGrammarLexer(stream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		metricsGrammarParser parser = new metricsGrammarParser(tokenStream,mc);
		try {
			return parser.evaluator();
		} catch (RecognitionException e) {
			// Bad Stuff happened!
			e.printStackTrace();
		}
		return 0;
	}	
}