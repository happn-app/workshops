//
//  SelectedList.swift
//  pokecard
//
//  Created by Romain Talleu on 21/05/2025.
//

import Foundation
import SwiftUI

struct SelectedList: View {

	@AppStorage(AppSettings.selectedCards) var selectedCards: [Int] = []
	@State private var pokemons: [Pokemon] = []

	private let columns: [GridItem] = Array(repeating: GridItem(.flexible(minimum:100)), count: 2)

	var body: some View {
		ScrollView {
			LazyVGrid(columns: [GridItem(.flexible(minimum: 100)), GridItem(.flexible(minimum: 100))]) {
				ForEach(pokemons.filter { selectedCards.contains($0.id) }) { pokemon in
					ZStack(alignment: .bottom) {
						PokemonCell(pokemon: pokemon)
							.grayscale(selectedCards.contains(pokemon.id) ? 0 : 1)
					}.onTapGesture {
						selectedCards.removeAll { $0 == pokemon.id }
					}
				}
			}
		}
		.onAppear {
			Task {
				pokemons = (try? await LocalRepository().fetchPokemons()) ?? []
			}
		}
	}
}
