package com.github.kotlin.everywhere.json.decode

fun <T1, U> map(decoder1: Decoder<T1>, mapper: (T1) -> U): Decoder<U> {
    return { element ->
        decoder1(element).map { t1 -> mapper(t1) }
    }
}

fun <T1, T2, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, mapper: (T1, T2) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).map { t2 -> mapper(t1, t2) }
        }
    }
}

fun <T1, T2, T3, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, mapper: (T1, T2, T3) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).map { t3 -> mapper(t1, t2, t3) }
            }
        }
    }
}

fun <T1, T2, T3, T4, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, mapper: (T1, T2, T3, T4) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).map { t4 -> mapper(t1, t2, t3, t4) }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, mapper: (T1, T2, T3, T4, T5) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).map { t5 -> mapper(t1, t2, t3, t4, t5) }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, mapper: (T1, T2, T3, T4, T5, T6) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).map { t6 -> mapper(t1, t2, t3, t4, t5, t6) }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, mapper: (T1, T2, T3, T4, T5, T6, T7) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).map { t7 -> mapper(t1, t2, t3, t4, t5, t6, t7) }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).map { t8 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8) }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).map { t9 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9) }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, decoder10: Decoder<T10>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).andThen { t9 ->
                                            decoder10(element).map { t10 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10) }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, decoder10: Decoder<T10>, decoder11: Decoder<T11>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).andThen { t9 ->
                                            decoder10(element).andThen { t10 ->
                                                decoder11(element).map { t11 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11) }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, decoder10: Decoder<T10>, decoder11: Decoder<T11>, decoder12: Decoder<T12>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).andThen { t9 ->
                                            decoder10(element).andThen { t10 ->
                                                decoder11(element).andThen { t11 ->
                                                    decoder12(element).map { t12 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12) }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, decoder10: Decoder<T10>, decoder11: Decoder<T11>, decoder12: Decoder<T12>, decoder13: Decoder<T13>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).andThen { t9 ->
                                            decoder10(element).andThen { t10 ->
                                                decoder11(element).andThen { t11 ->
                                                    decoder12(element).andThen { t12 ->
                                                        decoder13(element).map { t13 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13) }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, decoder10: Decoder<T10>, decoder11: Decoder<T11>, decoder12: Decoder<T12>, decoder13: Decoder<T13>, decoder14: Decoder<T14>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).andThen { t9 ->
                                            decoder10(element).andThen { t10 ->
                                                decoder11(element).andThen { t11 ->
                                                    decoder12(element).andThen { t12 ->
                                                        decoder13(element).andThen { t13 ->
                                                            decoder14(element).map { t14 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14) }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, decoder10: Decoder<T10>, decoder11: Decoder<T11>, decoder12: Decoder<T12>, decoder13: Decoder<T13>, decoder14: Decoder<T14>, decoder15: Decoder<T15>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).andThen { t9 ->
                                            decoder10(element).andThen { t10 ->
                                                decoder11(element).andThen { t11 ->
                                                    decoder12(element).andThen { t12 ->
                                                        decoder13(element).andThen { t13 ->
                                                            decoder14(element).andThen { t14 ->
                                                                decoder15(element).map { t15 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15) }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, decoder10: Decoder<T10>, decoder11: Decoder<T11>, decoder12: Decoder<T12>, decoder13: Decoder<T13>, decoder14: Decoder<T14>, decoder15: Decoder<T15>, decoder16: Decoder<T16>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).andThen { t9 ->
                                            decoder10(element).andThen { t10 ->
                                                decoder11(element).andThen { t11 ->
                                                    decoder12(element).andThen { t12 ->
                                                        decoder13(element).andThen { t13 ->
                                                            decoder14(element).andThen { t14 ->
                                                                decoder15(element).andThen { t15 ->
                                                                    decoder16(element).map { t16 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16) }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, decoder10: Decoder<T10>, decoder11: Decoder<T11>, decoder12: Decoder<T12>, decoder13: Decoder<T13>, decoder14: Decoder<T14>, decoder15: Decoder<T15>, decoder16: Decoder<T16>, decoder17: Decoder<T17>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).andThen { t9 ->
                                            decoder10(element).andThen { t10 ->
                                                decoder11(element).andThen { t11 ->
                                                    decoder12(element).andThen { t12 ->
                                                        decoder13(element).andThen { t13 ->
                                                            decoder14(element).andThen { t14 ->
                                                                decoder15(element).andThen { t15 ->
                                                                    decoder16(element).andThen { t16 ->
                                                                        decoder17(element).map { t17 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17) }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, decoder10: Decoder<T10>, decoder11: Decoder<T11>, decoder12: Decoder<T12>, decoder13: Decoder<T13>, decoder14: Decoder<T14>, decoder15: Decoder<T15>, decoder16: Decoder<T16>, decoder17: Decoder<T17>, decoder18: Decoder<T18>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).andThen { t9 ->
                                            decoder10(element).andThen { t10 ->
                                                decoder11(element).andThen { t11 ->
                                                    decoder12(element).andThen { t12 ->
                                                        decoder13(element).andThen { t13 ->
                                                            decoder14(element).andThen { t14 ->
                                                                decoder15(element).andThen { t15 ->
                                                                    decoder16(element).andThen { t16 ->
                                                                        decoder17(element).andThen { t17 ->
                                                                            decoder18(element).map { t18 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18) }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, decoder10: Decoder<T10>, decoder11: Decoder<T11>, decoder12: Decoder<T12>, decoder13: Decoder<T13>, decoder14: Decoder<T14>, decoder15: Decoder<T15>, decoder16: Decoder<T16>, decoder17: Decoder<T17>, decoder18: Decoder<T18>, decoder19: Decoder<T19>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).andThen { t9 ->
                                            decoder10(element).andThen { t10 ->
                                                decoder11(element).andThen { t11 ->
                                                    decoder12(element).andThen { t12 ->
                                                        decoder13(element).andThen { t13 ->
                                                            decoder14(element).andThen { t14 ->
                                                                decoder15(element).andThen { t15 ->
                                                                    decoder16(element).andThen { t16 ->
                                                                        decoder17(element).andThen { t17 ->
                                                                            decoder18(element).andThen { t18 ->
                                                                                decoder19(element).map { t19 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19) }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, decoder10: Decoder<T10>, decoder11: Decoder<T11>, decoder12: Decoder<T12>, decoder13: Decoder<T13>, decoder14: Decoder<T14>, decoder15: Decoder<T15>, decoder16: Decoder<T16>, decoder17: Decoder<T17>, decoder18: Decoder<T18>, decoder19: Decoder<T19>, decoder20: Decoder<T20>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).andThen { t9 ->
                                            decoder10(element).andThen { t10 ->
                                                decoder11(element).andThen { t11 ->
                                                    decoder12(element).andThen { t12 ->
                                                        decoder13(element).andThen { t13 ->
                                                            decoder14(element).andThen { t14 ->
                                                                decoder15(element).andThen { t15 ->
                                                                    decoder16(element).andThen { t16 ->
                                                                        decoder17(element).andThen { t17 ->
                                                                            decoder18(element).andThen { t18 ->
                                                                                decoder19(element).andThen { t19 ->
                                                                                    decoder20(element).map { t20 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20) }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, decoder10: Decoder<T10>, decoder11: Decoder<T11>, decoder12: Decoder<T12>, decoder13: Decoder<T13>, decoder14: Decoder<T14>, decoder15: Decoder<T15>, decoder16: Decoder<T16>, decoder17: Decoder<T17>, decoder18: Decoder<T18>, decoder19: Decoder<T19>, decoder20: Decoder<T20>, decoder21: Decoder<T21>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).andThen { t9 ->
                                            decoder10(element).andThen { t10 ->
                                                decoder11(element).andThen { t11 ->
                                                    decoder12(element).andThen { t12 ->
                                                        decoder13(element).andThen { t13 ->
                                                            decoder14(element).andThen { t14 ->
                                                                decoder15(element).andThen { t15 ->
                                                                    decoder16(element).andThen { t16 ->
                                                                        decoder17(element).andThen { t17 ->
                                                                            decoder18(element).andThen { t18 ->
                                                                                decoder19(element).andThen { t19 ->
                                                                                    decoder20(element).andThen { t20 ->
                                                                                        decoder21(element).map { t21 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21) }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, U> map(decoder1: Decoder<T1>, decoder2: Decoder<T2>, decoder3: Decoder<T3>, decoder4: Decoder<T4>, decoder5: Decoder<T5>, decoder6: Decoder<T6>, decoder7: Decoder<T7>, decoder8: Decoder<T8>, decoder9: Decoder<T9>, decoder10: Decoder<T10>, decoder11: Decoder<T11>, decoder12: Decoder<T12>, decoder13: Decoder<T13>, decoder14: Decoder<T14>, decoder15: Decoder<T15>, decoder16: Decoder<T16>, decoder17: Decoder<T17>, decoder18: Decoder<T18>, decoder19: Decoder<T19>, decoder20: Decoder<T20>, decoder21: Decoder<T21>, decoder22: Decoder<T22>, mapper: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) -> U): Decoder<U> {
    return { element ->
        decoder1(element).andThen { t1 ->
            decoder2(element).andThen { t2 ->
                decoder3(element).andThen { t3 ->
                    decoder4(element).andThen { t4 ->
                        decoder5(element).andThen { t5 ->
                            decoder6(element).andThen { t6 ->
                                decoder7(element).andThen { t7 ->
                                    decoder8(element).andThen { t8 ->
                                        decoder9(element).andThen { t9 ->
                                            decoder10(element).andThen { t10 ->
                                                decoder11(element).andThen { t11 ->
                                                    decoder12(element).andThen { t12 ->
                                                        decoder13(element).andThen { t13 ->
                                                            decoder14(element).andThen { t14 ->
                                                                decoder15(element).andThen { t15 ->
                                                                    decoder16(element).andThen { t16 ->
                                                                        decoder17(element).andThen { t17 ->
                                                                            decoder18(element).andThen { t18 ->
                                                                                decoder19(element).andThen { t19 ->
                                                                                    decoder20(element).andThen { t20 ->
                                                                                        decoder21(element).andThen { t21 ->
                                                                                            decoder22(element).map { t22 -> mapper(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22) }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}