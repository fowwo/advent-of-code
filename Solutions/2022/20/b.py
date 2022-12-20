# December 20th, 2022

file = open(f"{__file__}/../input.txt", "r")
numbers = [ int(line.strip()) * 811589153 for line in file ]
file.close()

zero = (numbers.index(0), 0)
numbers = [ (i, numbers[i]) for i in range(len(numbers)) ]

l = [ x for x in numbers ]
for _ in range(10):
	for n in numbers:
		s = l.index(n)
		l = l[:s] + l[s + 1:]
		d = (s + n[1]) % len(l) 
		l = l[:d] + [ n ] + l[d:]

z = l.index(zero)
print(sum(l[(z + d) % len(l)][1] for d in [ 1000, 2000, 3000 ]))
