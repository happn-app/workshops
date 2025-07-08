//
//  DetailView.swift
//  pokecard
//
//  Created by Romain Talleu on 20/05/2025.
//

import Foundation
import SwiftUI

struct DetailView: View {

	let pokemon: PokemonCard

	var body: some View {
		VStack(spacing: 0) {
			Text(pokemon.name)
			Image(pokemon.image)
		}
	}
}

#Preview {
	DetailView(pokemon: .init(id: 1, name: "Bulbasaur"))
}
