# December 3rd, 2023

file = open(f"{__file__}/../input.txt", "r")
lines = [ line.split(": ")[1].strip() for line in file ]
cards = [ [ set(map(int, y.split())) for y in x.split(" | ") ] for x in lines ]

d = dict()
for i, [ winning, numbers ] in enumerate(cards, 1):
	v = sum(1 if x in winning else 0 for x in numbers)
	d[i] = list(range(i + 1, i + 1 + v))

for k, v in sorted(d.items(), reverse = True):
	v = sum(d[p] for p in v)
	d[k] = v + 1
print(sum(d.values()))
