package com.example.lealgym.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lealgym.R
import com.example.lealgym.databinding.FragmentHomeBinding
import com.example.lealgym.databinding.FragmentRecoverBinding
import com.example.lealgym.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class HomeFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        configTablayout()
        click()
    }

    private fun configTablayout() {
        val adapter = ViewPagerAdapter(requireActivity())
        binding.viewPage.adapter = adapter

        adapter.addFragment(TodoFragment(), "A fazer")
        adapter.addFragment(DoingFragment(), "Fazendo")
        adapter.addFragment(DoneFragment(), "Feitas")

        binding.viewPage.offscreenPageLimit = adapter.itemCount

        TabLayoutMediator(
            binding.tabs, binding.viewPage
        ) { tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()
    }

    fun click() {
        binding.btLogout.setOnClickListener {
            logout()
        }
    }

    fun logout() {
        auth.signOut()
        findNavController().navigate(R.id.action_homeFragment_to_navigation2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}