# July 10th, 2022

file = open("input.txt", 'r')
input = file.read().strip().split(" ")
file.close()

players = int(input[0])
marbles = int(input[6]) + 1
game = [ 0 ]
scores = [ 0 for _ in range(players) ]
i = 0
for marble in range(1, marbles):
	if marble % 23 == 0:
		i -= 7
		i %= len(game)
		scores[(marble - 1) % players] += marble + game[i]
		del game[i]
	else:
		i += 1
		i %= len(game)
		game.insert(i + 1, marble)
		i += 1
print(max(scores))
