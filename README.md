# Haffman
(Huffman)

It's a program that implements Huffman's compression.
	You can start it with 2 types of arguments.

1. The path to the file that you want to compress.
	In this case you will have two new files in the folder of the original file. The first file will have the extension .hf and will be a compression of your file, and the second new file will be a special table without which your compressed file cannot be read
1. The path to the file that you want to decode. It must have ".hf" extension. And if you have a special table in the same folder, you get your unzipped file in the same folder with ".txt" extention