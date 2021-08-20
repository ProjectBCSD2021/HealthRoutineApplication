package com.example.healthroutineapplication

import com.example.healthroutineapplication.models.ExerciseData

fun getChestExerciseList(): List<Routines> {
    return listOf(
        Routines(
            id = 1,
            name = "푸시업",
            test =arrayOf("맨몸")
        ),
        Routines(
            id = 2,
            name = "딥스",
            test =arrayOf("맨몸","머신")
        ),
        Routines(
            id = 3,
            name = "플랫 벤치프레스",
            test =arrayOf("바벨","덤벨","머신","스미스 머신")
        ),
        Routines(
            id = 4,
            name = "인클라인 벤치프레스",
            test =arrayOf("바벨","덤벨","머신","스미스 머신")
        ),
        Routines(
            id = 5,
            name = "디클라인 벤치프레스",
            test =arrayOf("바벨","덤벨","머신","스미스 머신")
        ),
        Routines(
            id = 6,
            name = "플라이",
            test =arrayOf("덤벨","머신")
        ),
        Routines(
            id = 7,
            name = "케이블 크로스 오버",
            test =arrayOf("케이블")
        )
    )
}

fun getLetExerciseList(): List<Routines> {
    return listOf(
        Routines(
            id = 11,
            name = "풀업",
            test =arrayOf("맨몸","머신")
        ),
        Routines(
            id = 12,
            name = "렛풀다운",
            test = arrayOf("머신","케이블")
        ),
        Routines(
            id = 13,
            name = "로우",
            test = arrayOf("덤벨","바벨", "스미스 머신")
        ),
        Routines(
            id = 14,
            name = "시티드 로우",
            test = arrayOf("머신","케이블")
        )
    )
}

fun getShoulderExerciseList(): List<Routines> {
    return listOf(
        Routines(
            id = 21,
            name = "프론트 레이즈",
            test =arrayOf("덤벨","케이블")
        ),
        Routines(
            id = 22,
            name = "숄더 프레스",
            test =arrayOf("덤벨", "머신", "바벨", "스미스 머신")
        ),
        Routines(
            id = 23,
            name = "사이드 레터럴 레이즈",
            test =arrayOf("덤벨", "머신","케이블")
        ),
        Routines(
            id = 24,
            name = "벤트 오버 레터럴 레이즈",
            test =arrayOf("덤벨", "머신","케이블")
        )
    )
}

fun getArmExerciseList(): List<Routines> {
    return listOf(
        Routines(
            id = 31,
            name = "컬",
            test =arrayOf("덤벨", "바벨", "이지 바", "케이블")
        ),
        Routines(
            id = 32,
            name = "라잉 트라이셉스 익스텐션",
            test =arrayOf("이지 바")
        ),
        Routines(
            id = 33,
            name = "덤벨 킥백",
            test = arrayOf("덤벨")
        ),
        Routines(
            id = 34,
            name = "케이블 푸시 다운",
            test =arrayOf("케이블")
        ),
        Routines(
            id = 35,
            name = "오버 헤드 덤벨 프레스",
            test = arrayOf("덤벨")
        )
    )
}

fun getLegExerciseList(): List<Routines> {
    return listOf(
        Routines(
            id = 41,
            name = "스쿼트",
            test =  arrayOf("머신", "바벨", "스미스 머신")
        ),
        Routines(
            id = 42,
            name = "힙 쓰러스트",
            test =  arrayOf("머신", "바벨", "스미스 머신")
        ),
        Routines(
            id = 43,
            name = "레그 컬",
            test = arrayOf("머신")
        ),
        Routines(
            id = 44,
            name = "런지",
            test = arrayOf("덤벨", "맨몸","스미스 머신")
        ),
        Routines(
            id = 45,
            name = "레그 프레스",
            test = arrayOf("머신")
        ),
        Routines(
            id = 46,
            name = "레그 익스텐션",
            test =arrayOf("머신")
        )
    )
}

fun getAbsExerciseList(): List<Routines> {
    return listOf(
        Routines(
            id = 51,
            name = "크런치",
            test = arrayOf("맨몸")
        ),
        Routines(
            id = 52,
            name = "레그 레이즈",
            test = arrayOf("맨몸")
        ),
        Routines(
            id = 53,
            name = "행 인 레그 레이즈",
            test =arrayOf("맨몸")
        ),
        Routines(
            id = 54,
            name = "V 업",
            test = arrayOf("맨몸")
        ),
        Routines(
            id = 55,
            name = "플랭크",
            test =arrayOf("맨몸")
        )
    )
}

val exerciseList = mutableListOf<ExerciseData>()

var choiceRoutineList:List<ExerciseData>? = listOf<ExerciseData>()

