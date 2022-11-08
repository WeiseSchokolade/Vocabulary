package de.schoko.vocab.resources;

import java.lang.reflect.Field;
import java.util.Map;

public class StringLoader {
	public void load(Map<String, String> translations) {
		Field[] fields = Strings.class.getFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (translations.containsKey(field.getName())) {
				try {
					field.set(Strings.class, translations.get(field.getName()));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
