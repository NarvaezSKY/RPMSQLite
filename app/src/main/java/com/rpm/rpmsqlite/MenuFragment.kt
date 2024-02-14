package com.rpm.rpmsqlite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rpm.rpmsqlite.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    private var _binding:FragmentMenuBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}