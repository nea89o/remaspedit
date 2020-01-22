package remasp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Pattern;

import remasp.model.Addition;
import remasp.model.AllowNegative;
import remasp.model.Befehl;
import remasp.model.Division;
import remasp.model.End;
import remasp.model.Goto;
import remasp.model.JBelowZero;
import remasp.model.JNZero;
import remasp.model.Jzero;
import remasp.model.KeinGueltigerBefehlException;
import remasp.model.KeinGueltigerOperandException;
import remasp.model.Konfiguration;
import remasp.model.Load;
import remasp.model.Multiplikation;
import remasp.model.Operand;
import remasp.model.Store;
import remasp.model.Subtraktion;

public class ParsingUtil {

	@FunctionalInterface
	public interface BefehlsGenerator {
		Befehl generate(Operand operand, String einLabel, int startOffset, int endOffset);
	}

	public static Map<Pattern, BefehlsGenerator> jumpCommands = new HashMap<Pattern, BefehlsGenerator>() {
		{
			put(Pattern.compile("JZERO", 2), Jzero::new);
			put(Pattern.compile("JNZERO", 2), JNZero::new);
			put(Pattern.compile("GOTO", 2), Goto::new);
			put(Pattern.compile("JNEG", 2), JBelowZero::new);
		}
	};

	public static Map<Pattern, BefehlsGenerator> regularCommands = new HashMap<Pattern, BefehlsGenerator>() {
		{
			put(Pattern.compile("LOAD", 2), Load::new);
			put(Pattern.compile("STORE", 2), Store::new);
			put(Pattern.compile("ADD", 2), Addition::new);
			put(Pattern.compile("SUB", 2), Subtraktion::new);
			put(Pattern.compile("MUL", 2), Multiplikation::new);
			put(Pattern.compile("DIV", 2), Division::new);
		}
	};
	public static Map<Pattern, BefehlsGenerator> singularCommands = new HashMap<Pattern, BefehlsGenerator>() {
		{
			put(Pattern.compile("END", 2), End::new);
			put(Pattern.compile("ALLOWNEG",2), AllowNegative::new);
		}
	};

	public static void erstelleProgrammBefehl(Konfiguration konfiguration, String zeilenText, int startOffset,
			int endOffset) throws NumberFormatException, Exception {
		String label = "";

		int kommentarStart = zeilenText.indexOf("//");
		if (kommentarStart != -1) {
			zeilenText = zeilenText.substring(0, kommentarStart);
		}

		zeilenText = zeilenText.trim().replaceAll("\t+", " ");
		zeilenText = zeilenText.replaceAll(" +", " ");
		List<String> befehlGesplittet = new ArrayList<>(Arrays.asList(zeilenText.split(" ")));

		if (zeilenText.isEmpty()) {
			return;
		}

		if (((String) befehlGesplittet.get(0)).endsWith(":")) {
			label = ((String) befehlGesplittet.get(0)).substring(0, ((String) befehlGesplittet.get(0)).length() - 1);
			befehlGesplittet.remove(0);
			if (befehlGesplittet.size() == 0) {
				throw new KeinGueltigerBefehlException("Ein Label muss vor einem Befehl stehen. ", startOffset,
						endOffset);
			}
		}

		if (befehlGesplittet.size() == 1) {

			Optional<BefehlsGenerator> singularCommand = singularCommands.entrySet().stream()
					.filter((entry) -> entry.getKey().matcher(befehlGesplittet.get(0)).matches()).map(Entry::getValue)
					.findFirst();
			if (singularCommand.isPresent()) {
				konfiguration.getBefehlsSpeicher()
						.add(singularCommand.get().generate(null, label, startOffset, endOffset));
			} else {
				throw new KeinGueltigerBefehlException("Kein Gültiger Befehl\n" + label + " " + befehlGesplittet.get(0),
						startOffset, endOffset);
			}
		}

		if (befehlGesplittet.size() == 2) {
			Operand operand;
			String befehlString = befehlGesplittet.get(0);
			String operandString = befehlGesplittet.get(1);

			Optional<BefehlsGenerator> jumpCommand = jumpCommands.entrySet().stream()
					.filter((entry) -> entry.getKey().matcher(befehlString).matches()).map(Entry::getValue).findFirst();
			Optional<BefehlsGenerator> regularCommand = regularCommands.entrySet().stream()
					.filter((entry) -> entry.getKey().matcher(befehlString).matches()).map(Entry::getValue).findFirst();

			try {

				if (jumpCommand.isPresent()) {
					operand = new Operand(operandString, konfiguration, true);
					konfiguration.getBefehlsSpeicher()
							.add(jumpCommand.get().generate(operand, label, startOffset, endOffset));
				} else if (regularCommand.isPresent()) {
					operand = new Operand(operandString, konfiguration, false);
					konfiguration.getBefehlsSpeicher()
							.add(regularCommand.get().generate(operand, label, startOffset, endOffset));
				} else {
					throw new KeinGueltigerBefehlException(
							"kein gültiger Befehl\n" + label + " " + befehlString + " " + operandString, startOffset,
							endOffset);
				}

			} catch (Exception e) {
				throw new KeinGueltigerOperandException("Kein gültiger Operand: " + operandString, startOffset,
						endOffset);
			}

		}

		if (befehlGesplittet.size() >= 3) {
			String falscherBefehl = "";
			for (String s : befehlGesplittet) {
				falscherBefehl = falscherBefehl + s + " ";
			}
			throw new KeinGueltigerBefehlException("Kein gültiger Befehl\n" + falscherBefehl, startOffset, endOffset);
		}
	}

}
