package main;

import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.LineUnavailableException;

//Esta classe irá lidar com a lógica do tradutor
public class Controller {
	
	private HashMap<Character, String> morseCodeMap;
	
	public Controller() {
		morseCodeMap = new HashMap<>();
		
		morseCodeMap.put('A', ".-");
		morseCodeMap.put('B', "-...");
		morseCodeMap.put('C', "-.-.");
		morseCodeMap.put('D', "-..");
		morseCodeMap.put('E', ".");
		morseCodeMap.put('F', "..-.");
		morseCodeMap.put('G', "--.");
		morseCodeMap.put('H', "....");
		morseCodeMap.put('I', "..");
		morseCodeMap.put('J', ".---");
		morseCodeMap.put('K', "-.-");
		morseCodeMap.put('L', ".-..");
		morseCodeMap.put('M', "--");
		morseCodeMap.put('N', "-.");
		morseCodeMap.put('O', "---");
		morseCodeMap.put('P', ".--.");
		morseCodeMap.put('Q', "--.-");
		morseCodeMap.put('R', ".-.");
		morseCodeMap.put('S', "...");
		morseCodeMap.put('T', "-");
		morseCodeMap.put('U', "..-");
		morseCodeMap.put('V', "...-");
		morseCodeMap.put('W', ".--");
		morseCodeMap.put('X', "-..-");
		morseCodeMap.put('Y', "-.--");
		morseCodeMap.put('Z', "--..");

		// Letras minúsculas
		morseCodeMap.put('a', ".-");
		morseCodeMap.put('b', "-...");
		morseCodeMap.put('c', "-.-.");
		morseCodeMap.put('d', "-..");
		morseCodeMap.put('e', ".");
		morseCodeMap.put('f', "..-.");
		morseCodeMap.put('g', "--.");
		morseCodeMap.put('h', "....");
		morseCodeMap.put('i', "..");
		morseCodeMap.put('j', ".---");
		morseCodeMap.put('k', "-.-");
		morseCodeMap.put('l', ".-..");
		morseCodeMap.put('m', "--");
		morseCodeMap.put('n', "-.");
		morseCodeMap.put('o', "---");
		morseCodeMap.put('p', ".--.");
		morseCodeMap.put('q', "--.-");
		morseCodeMap.put('r', ".-.");
		morseCodeMap.put('s', "...");
		morseCodeMap.put('t', "-");
		morseCodeMap.put('u', "..-");
		morseCodeMap.put('v', "...-");
		morseCodeMap.put('w', ".--");
		morseCodeMap.put('x', "-..-");
		morseCodeMap.put('y', "-.--");
		morseCodeMap.put('z', "--..");

		// Números
		morseCodeMap.put('0', "-----");
		morseCodeMap.put('1', ".----");
		morseCodeMap.put('2', "..---");
		morseCodeMap.put('3', "...--");
		morseCodeMap.put('4', "....-");
		morseCodeMap.put('5', ".....");
		morseCodeMap.put('6', "-....");
		morseCodeMap.put('7', "--...");
		morseCodeMap.put('8', "---..");
		morseCodeMap.put('9', "----.");

		// Caracteres especiais (pontuação padrão em Morse)
		morseCodeMap.put('.', ".-.-.-");
		morseCodeMap.put(',', "--..--");
		morseCodeMap.put('?', "..--..");
		morseCodeMap.put('\'', ".----.");
		morseCodeMap.put('!', "-.-.--");
		morseCodeMap.put('/', "-..-.");
		morseCodeMap.put('(', "-.--.");
		morseCodeMap.put(')', "-.--.-");
		morseCodeMap.put('&', ".-...");
		morseCodeMap.put(':', "---...");
		morseCodeMap.put(';', "-.-.-.");
		morseCodeMap.put('=', "-...-");
		morseCodeMap.put('+', ".-.-.");
		morseCodeMap.put('-', "-....-");
		morseCodeMap.put('_', "..--.-");
		morseCodeMap.put('"', ".-..-.");
		morseCodeMap.put('$', "...-..-");
		morseCodeMap.put('@', ".--.-.");
	}
	
	public String translateToMorse(String textToTranslate) {
		StringBuilder translatedText = new StringBuilder();
		for(Character letter : textToTranslate.toCharArray()) {
			translatedText.append(morseCodeMap.get(letter) + " ");
		}
		return translatedText.toString();
	}
	
	public void playSound(String[] morseMessage)
	        throws javax.sound.sampled.LineUnavailableException, InterruptedException {

	    // Propriedades do áudio
	    AudioFormat audioFormat = new AudioFormat(44100, 16, 1, true, false);

	    DataLine.Info dataLineInfo =
	            new DataLine.Info(SourceDataLine.class, audioFormat);

	    SourceDataLine sourceDataLine =
	            (SourceDataLine) AudioSystem.getLine(dataLineInfo);

	    sourceDataLine.open(audioFormat);
	    sourceDataLine.start();

	    int dotDuration = 200;
	    int dashDuration = (int) (1.5 * dotDuration);
	    int slashDuration = 2 * dashDuration;

	    for (String pattern : morseMessage) {
	        for (char c : pattern.toCharArray()) {

	            if (c == '.') {
	                playBeep(sourceDataLine, dotDuration);
	                Thread.sleep(dotDuration);

	            } else if (c == '-') {
	                playBeep(sourceDataLine, dashDuration);
	                Thread.sleep(dashDuration);

	            } else if (c == '/') {
	                Thread.sleep(slashDuration);
	            }
	        }

	        Thread.sleep(dotDuration);
	    }

	    sourceDataLine.drain();
	    sourceDataLine.stop();
	    sourceDataLine.close();
	}
	
	private void playBeep(SourceDataLine line, int duration) {
		byte[] data = new byte[duration * 4100 / 1000];
		
		for(int i = 0; i < data.length; i++) {
			 double angle = i / (44100/440) * 2 * Math.PI;
			 
			 data[i] = (byte) (Math.sin(angle) * 127);
		}
		
		line.write(data, 0, data.length);
	}
}
