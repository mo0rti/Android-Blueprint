package com.mortitech.blueprint.foundation.architecture

import android.view.LayoutInflater
import android.view.ViewGroup

typealias ActivityLayoutInflater<T> = (LayoutInflater) -> T
typealias FragmentLayoutInflater<T> = (LayoutInflater, ViewGroup?, Boolean) -> T