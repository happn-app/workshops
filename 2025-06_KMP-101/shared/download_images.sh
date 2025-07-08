#!/bin/bash

base_url="https://pokecardex.b-cdn.net/assets/images/sets/MEW/HD/"

start=1
end=207

output_dir="images"
mkdir -p "$output_dir"

for i in $(seq $start $end); do
    url="${base_url}${i}.jpg?class=hd"
    echo "Downloading $url..."
    formatted_number=$(printf "%03d" $i)
    curl -s -o "${output_dir}/image${formatted_number}.jpg" "$url"
done

echo "Download complete."
