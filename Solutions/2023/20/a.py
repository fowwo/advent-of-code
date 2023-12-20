# December 19th, 2023

file = open(f"{__file__}/../input.txt", "r")
lines = [ line.strip().split(" -> ") for line in file ]

d = dict()
ff = dict()
con = dict()
for a, b in lines:
	if a.startswith("%"):
		a = a[1:]
		ff[a] = 0
	elif a.startswith("&"):
		a = a[1:]
		con[a] = dict()
	d[a] = b.split(", ")

for a, b in d.items():
	for x in b:
		if x in con:
			con[x][a] = 0

low = high = 0
for _ in range(1000):
	low += 1
	m = [ ("broadcaster", 0) ]
	while m:
		n = []
		for k, p in m:
			if k in ff:
				if p: continue
				ff[k] ^= 1
				p = ff[k]
			elif k in con:
				p = 0 if all(con[k].values()) else 1

			if p: high += len(d[k])
			else: low += len(d[k])
			for x in d[k]:
				if x in d: n.append((x, p))
				if x in con: con[x][k] = p
		m = n
print(high * low)
