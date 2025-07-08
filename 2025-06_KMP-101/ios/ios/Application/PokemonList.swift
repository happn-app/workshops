//
//  PokemonList.swift
//  pokecard
//
//  Created by Romain Talleu on 21/05/2025.
//

import Foundation
import SwiftUI
import shared

struct PokemonList: View {

	@AppStorage(AppSettings.selectedCards) var selectedCards: [Int] = []
	@State private var pokemons: [PokemonCard] = []

	private let columns: [GridItem] = Array(repeating: GridItem(.flexible(minimum:100)), count: 2)

	var body: some View {
		NavigationStack {
			Group {
				if pokemons.isEmpty {
					Text("Loading...")
				} else {
					ScrollView {
						LazyVGrid(columns: columns) {
							ForEach(pokemons) { pokemon in
								ZStack(alignment: .bottom) {
									PokemonCell(pokemon: pokemon)
										.grayscale(selectedCards.contains(pokemon.id) ? 0 : 1)
								}.onTapGesture {
									if selectedCards.contains(pokemon.id) {
										selectedCards.removeAll { $0 == pokemon.id }
									} else {
										selectedCards.append(pokemon.id)
									}
								}
							}
						}
					}
				}
			}
			.onAppear {
				Task {
					pokemons = await fetchPokemons()
				}
			}
		}
	}
}

func fetchPokemons() async -> [PokemonCard] {
    let kmpPokemons = (try? await LocalPokemonRepository().getAll()) ?? []
    
    return kmpPokemons.map { pokemon in pokemon.toPokemonCard()}
}

extension Pokemon {
    func toPokemonCard() -> PokemonCard {
        .init(id: Int(id), name: name)
    }
}
