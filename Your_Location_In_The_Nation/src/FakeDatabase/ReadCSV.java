package FakeDatabase;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ReadCSV implements Closeable {
	private BufferedReader reader;
	
	public ReadCSV(String resourceName) throws IOException {
		//LocationDatares is the package that contains the all of the CSV files
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("LocationDatares/" + resourceName);
		if (in == null) {
			throw new IOException("Couldn't open " + resourceName);
		}
		this.reader = new BufferedReader(new InputStreamReader(in));
	}
	
	public List<String> next() throws IOException {
		String line = reader.readLine();
		if (line == null) {
			return null;
		}
		List<String> tuple = new ArrayList<String>();
		StringTokenizer tok = new StringTokenizer(line, "|");
		while (tok.hasMoreTokens()) {
			tuple.add(tok.nextToken().trim());
		}
		return tuple;
	}
	
	public void close() throws IOException {
		reader.close();
	}
}
