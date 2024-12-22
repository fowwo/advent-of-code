# December 21st, 2024

file = open(f"{__file__}/../input.txt", "r")

t = dict()
for line in file:
	x = int(line)
	prices = [ x % 10 ]
	for _ in range(2000):
		x ^= x * 64
		x %= 16777216
		x ^= x // 32
		x %= 16777216
		x ^= x * 2048 
		x %= 16777216
		prices.append(x % 10)

	d = dict()
	for i in range(len(prices) - 4):
		sequence = ",".join(map(str, (prices[i + 1] - prices[i] for i in range(i, i + 4))))
		if sequence not in d:
			d[sequence] = prices[i + 4]
	for sequence, price in d.items():
		if sequence not in t:
			t[sequence] = 0
		t[sequence] += price
file.close()

print(max(t.values()))
