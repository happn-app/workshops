//
//  LocalRepository.swift
//  pokecard
//
//  Created by Romain Talleu on 19/05/2025.
//

import Foundation

class LocalRepository: Repository {
	func fetchPokemons() async throws -> [PokemonCard] {
		guard let url = Bundle.main.url(forResource: "pokemon_list", withExtension: "json") else {
			throw RepositoryError.unknown
		}

		guard let data = try? Data(contentsOf: url) else {
			throw RepositoryError.unknown
		}

		let decoder = JSONDecoder()
		guard let list = try? decoder.decode([PokemonCard].self, from: data) else {
			throw RepositoryError.unknown
		}

		return list
	}
}
