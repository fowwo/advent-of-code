# December 24th, 2023

file = open(f"{__file__}/../input.txt", "r")

def f(x):
	v = 0
	for c in x:
		v += ord(c)
		v *= 17
		v %= 256
	return v

box = dict((i, dict()) for i in range(256))
for x in file.readline().strip().split(','):
	if x[-1] == '-':
		label = x[:-1]
		h = f(label)
		if label in box[h]: del box[h][label]
	else:
		label, v = x.split('=')
		h = f(label)
		box[h][label] = int(v) # dict preserves insertion order

v = 0
for n, b in box.items():
	for i, x in enumerate(b.values()):
		v += (n + 1) * (i + 1) * x
print(v)
