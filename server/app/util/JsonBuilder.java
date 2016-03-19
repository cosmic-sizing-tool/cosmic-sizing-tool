package util;

import java.util.LinkedList;
import java.util.List;

public class JsonBuilder {
	
	private abstract class JsonEntity{
		abstract public String toString();
		
		protected String serializeString(String str)
		{
			//todo escape str
			return "\"" + str + "\"";
		}
	};
	
	private class JsonIntEntity extends JsonEntity{
		public JsonIntEntity(String name, int value)
		{
			this.name = name;
			this.value = value;
		}
		
		@Override
		public String toString()
		{
			return serializeString(name) + ":" + Integer.toString(value);
		}
		
		private String name; 
		private int value;
	}
	
	private class JsonLongEntity extends JsonEntity{
		public JsonLongEntity(String name, long value)
		{
			this.name = name;
			this.value = value;
		}
		
		@Override
		public String toString()
		{
			return serializeString(name) + ":" + Long.toString(value);
		}
		
		private String name; 
		private long value;
	}
	
	private class JsonStringEntity extends JsonEntity{
		public JsonStringEntity(String name, String value)
		{
			this.name = name;
			this.value = value;
		}
		
		@Override
		public String toString()
		{
			return serializeString(name) + ":" + serializeString(value);
		}
		
		private String name; 
		private String value;
	}
	
	private class JsonJsonEntity extends JsonEntity{
		public JsonJsonEntity(String name, JsonBuilder value)
		{
			this.name = name;
			this.value = value;
		}
		
		@Override
		public String toString()
		{
			return serializeString(name) + ":" + value.toString();
		}
		
		private String name; 
		private JsonBuilder value;
	}
	
	private class JsonListEntity extends JsonEntity{
		public JsonListEntity(String name, List<? extends JsonSerializable> value)
		{
			this.name = name;
			this.value = value;
		}
		
		@Override
		public String toString()
		{
			String str = serializeString(name) + ":" + "[";
			boolean first = true;
			
			for( JsonSerializable js : value )
			{
				if(!first) str += ",";
				str += js.toJson();
				first = false;
			}
			
			str += "]";
			
			return str;
		}
		
		private String name; 
		private List<? extends JsonSerializable> value;
	}
	
	private List<JsonEntity> entities;
	
	public JsonBuilder(){
		entities = new LinkedList<JsonEntity>();
	}
	
	public void add(String name, int value)
	{
		entities.add( new JsonIntEntity(name, value) );
	}
	
	public void add(String name, long value)
	{
		entities.add( new JsonLongEntity(name, value) );
	}
	
	public void add(String name, String value)
	{
		entities.add( new JsonStringEntity(name, value) );
	}
	
	public void add(String name, JsonBuilder value)
	{
		entities.add( new JsonJsonEntity(name, value) );
	}
	
	public void add(String name, List<? extends JsonSerializable> value)
	{
		entities.add( new JsonListEntity(name, value) );
	}
	
	public String toString()
	{
		String str = "{";
		boolean first = true;
		
		for(JsonEntity e : entities)
		{
			if(!first)
			{
				str += ",";
			}
			
			str += e.toString();
			
			first = false;
		}
		
		str += "}";
		
		return str;
	}
}