//
//  PokemonView.swift
//  pokecard
//
//  Created by Romain Talleu on 21/05/2025.
//

import Foundation
import SwiftUI

struct PokemonCell: View {

	let pokemon: PokemonCard

	var body: some View {
		Image(pokemon.image)
			.resizable()
			.scaledToFit()
	}
}
