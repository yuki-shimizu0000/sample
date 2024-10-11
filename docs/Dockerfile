FROM debian:11

RUN apt-get update && apt-get install -y pandoc && apt-get clean

WORKDIR /usr/src/app

RUN mkdir -p /usr/src/app/docs

COPY ./database_design.md /usr/src/app/docs/

CMD ["pandoc", "/usr/src/app/docs/database_design.md", "-o", "/usr/src/app/docs/database_design.html"]
