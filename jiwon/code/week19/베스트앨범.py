def solution(genres, plays):
    # 각 장르의 총 재생 횟수를 저장
    genre_total_plays = {}

    # 각 장르별로 노래 정보를 저장
    genre_songs = {}

    # 장르별로 노래 정보를 분류
    for i in range(len(genres)):
        genre = genres[i]
        play = plays[i]

        if genre in genre_total_plays:
            genre_total_plays[genre] += play
        else:
            genre_total_plays[genre] = play

        if genre in genre_songs:
            genre_songs[genre].append((i, play))
        else:
            genre_songs[genre] = [(i, play)]

    # 총 재생 횟수를 기준으로 장르를 내림차순 정렬
    sorted_genres = sorted(genre_total_plays.keys(), key=lambda x: -genre_total_plays[x])

    result = []

    # 각 장르별로 상위 2곡을 선택하여 결과에 추가
    for genre in sorted_genres:
        songs_in_genre = genre_songs[genre]

        # 노래를 재생 횟수에 따라 내림차순 정렬하고 상위 2곡을 선택
        songs_in_genre = sorted(songs_in_genre, key=lambda x: (-x[1], x[0]))

        if len(songs_in_genre) >=2:
            result.append(songs_in_genre[0][0])
            result.append(songs_in_genre[1][0])
        elif len(songs_in_genre) == 1:
            result.append(songs_in_genre[0][0])

    return result


print(solution(["classic", "pop", "classic", "classic", "pop"], [500, 600, 150, 800, 2500]))