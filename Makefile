client-setup:
	cd client && npm install && bower install

client:
	cd client && grunt serve

server:
	cd server; \
	sbt run || activator run

.PHONY: client server
