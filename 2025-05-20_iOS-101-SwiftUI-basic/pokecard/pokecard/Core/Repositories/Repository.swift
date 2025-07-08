//
//  Repository.swift
//  pokecard
//
//  Created by Romain Talleu on 19/05/2025.
//

import Foundation


protocol Repository {
	func fetchPokemons() async throws -> [Pokemon]
}

enum RepositoryError: Error {
	case unknown
}
