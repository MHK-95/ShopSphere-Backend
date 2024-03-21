package com.shop.services.DTO;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDTO {
	
	private int id;
	private int userId;
	private List<ProductDTO> products;
	
}
