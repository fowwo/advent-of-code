# August 1st, 2022

memo = set()
prev = None

e = 0
while True:
	d = e | 65536
	e = 14464005
	while True:
		e += d & 255
		e &= 16777215
		e *= 65899
		e &= 16777215
		if d < 256:
			break
		d //= 256
	if e in memo:
		print(prev)
		break
	memo.add(e)
	prev = e
