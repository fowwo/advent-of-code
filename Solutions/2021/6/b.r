# August 28th, 2022

fish <- scan("input.txt", sep=",")

fact <- function(x) {
	y <- 1
	i <- 2
	while (i <= x) {
		y <- y * i
		i <- i + 1
	}
	y
}
p <- function(x,k) {
	product <- 1
	n <- 0
	while (n <= k - 1) {
		product <- product * ((x - 9 * k + 6) %/% 7 + n)
		n <- n + 1
	}
	product / fact(k)
}

s <- list()
for (i in 1:5) {
	s[[i]] <- 0
	value <- -1
	n <- 0
	while (value != 0) {
		value <- p(256 + 9 - i, n)
		s[[i]] <- s[[i]] + value
		n <- n + 1
	}
}

sum <- 0
for (f in fish) {
	sum <- sum + s[[f]]
}
print(sum, digits=16)
