package se.kth.iv1350.model;

import java.util.Objects;

public class ItemIdDTO
{
	private String identifier;

	public ItemIdDTO(String identifier)
	{
		this.identifier = identifier;
	}

	public String getIdentifier()
	{
		return identifier;
	}

	@Override
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}

		if (object == null)
		{
			return false;
		}

		if (!(object instanceof ItemIdDTO))
		{
			return false;
		}

		ItemIdDTO itemId = (ItemIdDTO)object;

		return itemId.identifier.equals(this.identifier);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(identifier);
	}

	@Override
	public String toString()
	{
		return identifier;
	}
}
