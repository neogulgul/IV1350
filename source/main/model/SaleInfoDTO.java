package se.kth.iv1350.model;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

public class SaleInfoDTO
{
	private Map<ItemIdDTO, RecordedItem> recordedItems = new HashMap<>();
	private SaleStringLengthInfoDTO stringLengthInfo;

	public SaleInfoDTO(
		Map<ItemIdDTO, RecordedItem> recordedItems,
		SaleStringLengthInfoDTO stringLengthInfo
	)
	{
		this.recordedItems    = recordedItems;
		this.stringLengthInfo = stringLengthInfo;
	}

	public Collection<RecordedItem> getCollectionOfRecordedItems()
	{
		return recordedItems.values();
	}

	public SaleStringLengthInfoDTO getStringLengthInfo()
	{
		return stringLengthInfo;
	}
}
