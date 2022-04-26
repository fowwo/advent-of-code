# April 25th, 2022

file = open("input.txt", 'r')

deck = [i for i in range(0, 10007)]
for line in file:
	line = line.strip()
	if line.startswith("cut"):
		value = int(line.split(" ")[-1])
		deck = deck[value:] + deck[:value]
	elif line.startswith("deal with"):
		value = int(line.split(" ")[-1])
		copy = [x for x in deck]
		p = 0
		for i in range(len(deck)):
			deck[p] = copy[i]
			p = (p + value) % len(deck)
	else:
		deck.reverse()
print(deck.index(2019))
