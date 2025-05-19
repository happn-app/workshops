//
//  Pokemon.swift
//  pokecard
//
//  Created by Romain Talleu on 19/05/2025.
//

struct Pokemon: Decodable {
	let id: Int
	let name: String

	var image: String {
		return "image\(id.formatBinary)"
	}
}
