package se.kth.iv1350.model;

import java.util.Objects;

/**
 * DTO used for representing an items id.
 */
public class ItemIdDTO
{
	private String identifier;

	/**
	 * Constructs a new {@link ItemIdDTO}.
	 *
	 * @param identifier <code>String</code> identifier.
	 */
	public ItemIdDTO(String identifier)
	{
		this.identifier = identifier;
	}

	/**
	 * Getter for identifier.
	 *
	 * @return <code>String</code> with identifier.
	 */
	public String getIdentifier()
	{
		return identifier;
	}

	/**
	 * Overriden equals method which is used in hashmaps, amongst other things.
	 *
	 * @return Whether an ItemIdDTO and another object is equal to each other.
	 */
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

	/**
	 * Overriden hashCode method which is used in hashmaps, amongst other things.
	 *
	 * @return <code>int</code> representing the hash code.
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(identifier);
	}

	/**
	 * Overriden toString method used when printing, amongst other things.
	 *
	 * @return {@link ItemIdDTO} as a string.
	 */
	@Override
	public String toString()
	{
		return identifier;
	}
}
