# December 6th, 2023

from functools import cmp_to_key

file = open(f"{__file__}/../input.txt", "r")
hands = [ line.strip().split() for line in file ]

def p(x):
	if x.isdigit(): return int(x) - 2
	return { "T": 8, "J": 9, "Q": 10, "K": 11, "A": 12 }[x]

fiveOfAKind = []
fourOfAKind = []
fullHouse = []
threeOfAKind = []
twoPair = []
onePair = []
highCard = []

for [ hand, bid ] in hands:
	c = [ 0 ] * 13
	for x in hand: c[p(x)] += 1
	if 5 in c: fiveOfAKind.append((hand, bid))
	elif 4 in c: fourOfAKind.append((hand, bid))
	elif 3 in c:
		if 2 in c: fullHouse.append((hand, bid))
		else: threeOfAKind.append((hand, bid))
	elif 2 in c:
		if 2 in c[c.index(2) + 1:]: twoPair.append((hand, bid))
		else: onePair.append((hand, bid))
	else: highCard.append((hand, bid))

def compare(a, b):
	for x, y in zip(a[0], b[0]):
		if (v := p(x) - p(y)):
			return v

key = cmp_to_key(compare)
fiveOfAKind.sort(key = key)
fourOfAKind.sort(key = key)
threeOfAKind.sort(key = key)
twoPair.sort(key = key)
onePair.sort(key = key)
fullHouse.sort(key = key)
highCard.sort(key = key)

hands = highCard + onePair + twoPair + threeOfAKind + fullHouse + fourOfAKind + fiveOfAKind
print(sum(i * int(bid) for i, (hand, bid) in enumerate(hands, 1)))
