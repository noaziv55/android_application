// Generated by view binder compiler. Do not edit!
package com.example.androidapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.androidapplication.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySettingsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnSave;

  @NonNull
  public final FrameLayout layoutImage;

  @NonNull
  public final LinearLayout linearLayout3;

  @NonNull
  public final EditText settingsServerAddress;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final CircleImageView userImageProfileImage;

  @NonNull
  public final TextView userTextNickname;

  private ActivitySettingsBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnSave,
      @NonNull FrameLayout layoutImage, @NonNull LinearLayout linearLayout3,
      @NonNull EditText settingsServerAddress, @NonNull TextView textView,
      @NonNull TextView textView3, @NonNull CircleImageView userImageProfileImage,
      @NonNull TextView userTextNickname) {
    this.rootView = rootView;
    this.btnSave = btnSave;
    this.layoutImage = layoutImage;
    this.linearLayout3 = linearLayout3;
    this.settingsServerAddress = settingsServerAddress;
    this.textView = textView;
    this.textView3 = textView3;
    this.userImageProfileImage = userImageProfileImage;
    this.userTextNickname = userTextNickname;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySettingsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySettingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_settings, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySettingsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnSave;
      Button btnSave = ViewBindings.findChildViewById(rootView, id);
      if (btnSave == null) {
        break missingId;
      }

      id = R.id.layoutImage;
      FrameLayout layoutImage = ViewBindings.findChildViewById(rootView, id);
      if (layoutImage == null) {
        break missingId;
      }

      id = R.id.linearLayout3;
      LinearLayout linearLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout3 == null) {
        break missingId;
      }

      id = R.id.settingsServerAddress;
      EditText settingsServerAddress = ViewBindings.findChildViewById(rootView, id);
      if (settingsServerAddress == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.user_image_profile_image;
      CircleImageView userImageProfileImage = ViewBindings.findChildViewById(rootView, id);
      if (userImageProfileImage == null) {
        break missingId;
      }

      id = R.id.user_text_nickname;
      TextView userTextNickname = ViewBindings.findChildViewById(rootView, id);
      if (userTextNickname == null) {
        break missingId;
      }

      return new ActivitySettingsBinding((ConstraintLayout) rootView, btnSave, layoutImage,
          linearLayout3, settingsServerAddress, textView, textView3, userImageProfileImage,
          userTextNickname);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
