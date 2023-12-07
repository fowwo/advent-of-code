# December 6th, 2023

from functools import cmp_to_key

file = open(f"{__file__}/../input.txt", "r")
hands = [ line.strip().split() for line in file ]

def p(x):
	if x.isdigit(): return int(x) - 1
	return { "T": 9, "J": 0, "Q": 10, "K": 11, "A": 12 }[x]

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
	j = c.pop(0)
	m = max(c) + j
	if m == 5: fiveOfAKind.append((hand, bid))
	elif m == 4: fourOfAKind.append((hand, bid))
	elif 2 in c and (3 in c or (2 in c[c.index(2) + 1:] and j == 1)): fullHouse.append((hand, bid))
	elif m == 3: threeOfAKind.append((hand, bid))
	elif 2 in c and 2 in c[c.index(2) + 1:]: twoPair.append((hand, bid))
	elif m == 2: onePair.append((hand, bid))
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
