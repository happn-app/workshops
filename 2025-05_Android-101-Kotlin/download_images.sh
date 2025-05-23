#!/bin/bash

base_url="https://pokecardex.b-cdn.net/assets/images/sets/BS/HD/"

start=1
end=102

output_dir="app/src/main/res/drawable"

for i in $(seq $start $end); do
    url="${base_url}${i}.jpg?class=hd"
    echo "Downloading $url..."
    formatted_number=$(printf "%03d" $i)
    curl -s -o "${output_dir}/card_${formatted_number}.jpg" "$url"
done

echo "Download complete."
