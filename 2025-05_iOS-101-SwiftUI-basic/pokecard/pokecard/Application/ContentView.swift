//
//  ContentView.swift
//  pokecard
//
//  Created by Romain Talleu on 14/05/2025.
//

import Foundation
import SwiftUI

struct ContentView: View {

	var body: some View {
		TabView {
			PokemonList()
				.tabItem {
					Label("Pokémons", systemImage: "tortoise")
				}

			SelectedList()
				.tabItem {
					Label("Starred", systemImage: "list.star")
				}
		}
	}
}

#Preview {
	ContentView()
}
